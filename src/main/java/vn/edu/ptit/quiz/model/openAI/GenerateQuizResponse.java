package vn.edu.ptit.quiz.model.openAI;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateQuizResponse  implements Serializable {
    private String id;
    private String object;
    private String created;
    private String model;
    private List<ChoiceResponse> choices;
    private UsageResponse usage;
    private String system_fingerprint;
}
