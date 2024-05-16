package vn.edu.ptit.quiz.model.openAI;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsageResponse implements Serializable {
    private Integer prompt_tokens;
    private Integer completion_tokens;
    private Integer total_tokens;
}
