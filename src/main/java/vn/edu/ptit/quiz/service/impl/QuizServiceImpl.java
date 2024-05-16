package vn.edu.ptit.quiz.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vn.edu.ptit.quiz.config.AppConstant;
import vn.edu.ptit.quiz.entity.Answer;
import vn.edu.ptit.quiz.entity.Category;
import vn.edu.ptit.quiz.entity.Question;
import vn.edu.ptit.quiz.entity.Quiz;
import vn.edu.ptit.quiz.exception.NotFoundException;
import vn.edu.ptit.quiz.model.*;
import vn.edu.ptit.quiz.model.openAI.*;
import vn.edu.ptit.quiz.repository.AnswerRepo;
import vn.edu.ptit.quiz.repository.CategoryRepo;
import vn.edu.ptit.quiz.repository.QuestionRepo;
import vn.edu.ptit.quiz.repository.QuizRepo;
import vn.edu.ptit.quiz.service.QuizService;
import vn.edu.ptit.quiz.util.converter.ConverterUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepo quizRepo;

    private final QuestionRepo questionRepo;

    private final AnswerRepo answerRepo;

    private final CategoryRepo categoryRepo;

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    @Value("${openai.api.key}")
    private String apiKey;
    @Override
    @Transactional
    public QuizDto createQuiz(Long categoryId, QuizAM quizAM) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category not found"));
        Quiz quiz = ConverterUtil.mappingToObject(quizAM, Quiz.class);
        quiz.setCategory(category);
        quizRepo.save(quiz);
        for(Question question : quiz.getQuestions()){
            question.setQuiz(quiz);
            questionRepo.save(question);
            for(Answer answer : question.getListAnswers()){
                answer.setQuestion(question);
                answerRepo.save(answer);
            }
            question.setListAnswers(question.getListAnswers());
        }
        quiz.setQuestions(quiz.getQuestions());
        quiz.setStatus(AppConstant.QUIZ.STATUS.NOT_ACTIVE);

        return ConverterUtil.mappingToObject(quiz, QuizDto.class);
    }


    @Override
    public QuizDto getQuizById(Integer quizId) {
        Optional<Quiz> quiz = Optional.ofNullable(quizRepo.findById(quizId)
                .orElseThrow(() -> new NotFoundException("Quiz not found by id " + quizId)));
        return ConverterUtil.mappingToObject(quiz, QuizDto.class);
    }

    @Override
    public List<QuizNameDto> getAllQuizByCategory(Integer categoryId) {
        Set<Quiz> quizSet = quizRepo.getAllQuizByCategoryId(categoryId);
        return ConverterUtil.mapList(Arrays.asList(quizSet.toArray()), QuizNameDto.class);
    }

    @Override
    @Transactional
    public QuizDto generateQuiz(Long categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category not found"));

        String urlOpenAPI = "https://api.openai.com/v1/chat/completions";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");
        GenerateQuizRequest generateQuizRequest = new GenerateQuizRequest();
        generateQuizRequest.setModel("gpt-3.5-turbo-1106");
        generateQuizRequest.setMax_tokens(1000);
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setRole("system");
        messageRequest.setContent("Tạo 5 câu hỏi trắc nghiệm chủ đề " + category.getCategoryName() + " có 4 đáp án và 1 đáp án đúng, " +
                "dữ liệu trả về dạng json restAPI và không chứa bất kỳ dấu '\\' và '\\n' nào " +
                "ngôn ngữ tiếng anh gồm 1 danh sách 5 câu hỏi, " +
                "mỗi câu hỏi gồm các key: 'question', 'answer_1', 'answer_2', 'answer_3', 'answer_4', 'correct'. " +
                "Với correct sẽ là số tương ứng với đáp án đúng là 1, 2, 3 hoặc 4. " +
                "Câu hỏi ngắn gọn trong 3 đến 6 từ, câu trả lời tối đa 4 từ");
        List<MessageRequest> messageRequests = new ArrayList<>();
        messageRequests.add(messageRequest);

        generateQuizRequest.setMessages(messageRequests);
        HttpEntity<GenerateQuizRequest> entity = new HttpEntity<>(generateQuizRequest, headers);

        int count = 0;
        while(count < 3) {
            ResponseEntity<String> response = restTemplate.exchange(
                    urlOpenAPI,
                    HttpMethod.POST,
                    entity,
                    String.class
            );
            try {
                if (response.getStatusCode().isError()) {
                    count++;
                    continue;
//                    throw new RuntimeException("Error when generate Quiz");
                }

                String res = response.getBody().replace("\n", "");
                res = res.replace("\\n", "");
                GenerateQuizResponse quizResponse = objectMapper.readValue(res, GenerateQuizResponse.class);
                String content = quizResponse.getChoices().get(0).getMessage().getContent();
                ContentResponse contentResponse = objectMapper.readValue(content, ContentResponse.class);

                Quiz quiz = new Quiz();
                quiz.setCategory(category);

                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmm");
                String formattedDateTime = currentDateTime.format(formatter);

                quiz.setTitle("Quiz " + category.getCategoryName() + " " + formattedDateTime);
                quiz.setStatus(1);
                quiz.setDescription("Quiz " + category.getCategoryName());
                Set<Question> questions = new HashSet<>();
                for (QuestionResponse questionResponse : contentResponse.getQuestions()) {
                    Question question = new Question();
                    question.setQuestion(questionResponse.getQuestion());
                    question.setLevel(1);
                    question.setPoint(2);
                    question.setMaxTime(10);
                    question.setListAnswers(new HashSet<>());
                    Answer answer1 = new Answer();
                    answer1.setAnswer(questionResponse.getAnswer_1());
                    answer1.setIsCorrect(questionResponse.getCorrect() == 1);
                    answer1.setQuestion(question);
                    answerRepo.save(answer1);
                    question.getListAnswers().add(answer1);

                    Answer answer2 = new Answer();
                    answer2.setAnswer(questionResponse.getAnswer_2());
                    answer2.setIsCorrect(questionResponse.getCorrect() == 2);
                    answer2.setQuestion(question);
                    answerRepo.save(answer2);
                    question.getListAnswers().add(answer2);

                    Answer answer3 = new Answer();
                    answer3.setAnswer(questionResponse.getAnswer_3());
                    answer3.setIsCorrect(questionResponse.getCorrect() == 3);
                    answer3.setQuestion(question);
                    answerRepo.save(answer3);
                    question.getListAnswers().add(answer3);

                    Answer answer4 = new Answer();
                    answer4.setAnswer(questionResponse.getAnswer_4());
                    answer4.setIsCorrect(questionResponse.getCorrect() == 4);
                    answer4.setQuestion(question);
                    answerRepo.save(answer4);
                    question.getListAnswers().add(answer4);

                    question.setQuiz(quiz);
                    questionRepo.save(question);
                    questions.add(question);
                }
                quiz.setQuestions(questions);

                quizRepo.save(quiz);
                return ConverterUtil.mappingToObject(quiz, QuizDto.class);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
                count++;
            }
        }
        throw new RuntimeException("Error when generate Quiz after 3 times");
    }


//    @Override
//    public QuizDto startQuiz(Integer quizId, StartQuizRequest startQuizRequest) {
//        Quiz quiz = quizRepo.findById(quizId)
//                .orElseThrow(() -> new UnAuthorizedException("UnAuthorized"));
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        try {
//            Date startTime = dateFormat.parse(startQuizRequest.getStartAt());
//            Date endTime = dateFormat.parse(startQuizRequest.getEndAt());
//            quiz.setStartAt(new Timestamp(startTime.getTime()));
//            quiz.setEndAt(new Timestamp(endTime.getTime()));
//        } catch (ParseException e) {
//            e.printStackTrace();
//
//        }
//
//        quiz.setStatus(AppConstant.QUIZ.STATUS.ACTIVE);
//        quizRepo.save(quiz);
//        return ConverterUtil.mappingToObject(quiz, QuizDto.class);
//    }
//
//    @Override
//    public QuizDto stopQuiz(Integer quizId) {
//        Quiz quiz = quizRepo.findById(quizId)
//                .orElseThrow(() -> new UnAuthorizedException("UnAuthorized"));
//        quiz.setStartAt(null);
//        quiz.setStatus(AppConstant.QUIZ.STATUS.NOT_ACTIVE);
//        quizRepo.save(quiz);
//        return ConverterUtil.mappingToObject(quiz, QuizDto.class);
//    }

}
