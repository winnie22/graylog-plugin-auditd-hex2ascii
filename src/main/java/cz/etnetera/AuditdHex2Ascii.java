package cz.etnetera;

import org.graylog.plugins.pipelineprocessor.EvaluationContext;
import org.graylog.plugins.pipelineprocessor.ast.expressions.Expression;
import org.graylog.plugins.pipelineprocessor.ast.functions.Function;
import org.graylog.plugins.pipelineprocessor.ast.functions.FunctionArgs;
import org.graylog.plugins.pipelineprocessor.ast.functions.FunctionDescriptor;
import org.graylog.plugins.pipelineprocessor.ast.functions.ParameterDescriptor;
import org.graylog.plugins.pipelineprocessor.ast.functions.AbstractFunction;

/**
 * This is the plugin. Your class should implement one of the existing plugin
 * interfaces. (i.e. AlarmCallback, MessageInput, MessageOutput)
 */
public class AuditdHex2Ascii implements Function<String> {

    public static final String NAME = "hex2ascii";
    private static final String PARAM = "hexcode";

    private final ParameterDescriptor<String, String> valueParam = ParameterDescriptor
            .string(PARAM)
            .description("Input string in hex-encoded format")
            .build();

    @Override
    public Object preComputeConstantArgument(FunctionArgs functionArgs, String s, Expression expression) {
        return expression.evaluateUnsafe(EvaluationContext.emptyContext());
    }

    @Override
    public String evaluate(FunctionArgs functionArgs, EvaluationContext evaluationContext) {
        String target = valueParam.required(functionArgs, evaluationContext);

        if (target == null || ! target.matches("^[0-9a-fA-F]+$") || target.length()%2!=0) {
            //non-hex input
            return "Error - input is not in hexformat!";
        }

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < target.length(); i = i + 2) {
            String s = target.substring(i, i + 2);
            int n = Integer.valueOf(s, 16);
            if(n == 0) {
                output.append(" ");
            }
            else {
                output.append((char)n);
            }
        }

        return String.valueOf(output);
    }

    @Override
    public FunctionDescriptor<String> descriptor() {
        return FunctionDescriptor.<String>builder()
                .name(NAME)
                .description("Convert hex-encoded string into ascii string")
                .params(valueParam)
                .returnType(String.class)
                .build();
    }

}
