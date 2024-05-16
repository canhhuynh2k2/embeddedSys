package vn.edu.ptit.quiz.model.openAI;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceResponse implements Serializable {
    private Integer index;
    private MessageResponse message;
    private String logprobs;
    private String finish_reason;
}
