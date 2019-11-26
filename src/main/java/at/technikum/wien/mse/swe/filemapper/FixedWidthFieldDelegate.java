package at.technikum.wien.mse.swe.filemapper;

import static org.apache.commons.lang.StringUtils.stripEnd;
import static org.apache.commons.lang.StringUtils.stripStart;

class FixedWidthFieldDelegate<T> extends FieldDelegate<T> {
    private final int startIndex;
    private final int length;
    private final String paddingChar;
    private final FieldAlignment alignment;

    FixedWidthFieldDelegate(Class<T> fieldClass, String fieldName, int startIndex, int length, String padding, FieldAlignment alignment) {
        super(fieldClass, fieldName);
        this.startIndex = startIndex;
        this.length = length;
        this.paddingChar = padding;
        this.alignment = alignment;
    }

    @Override
    protected T loadValue(String lineContent) {
        if (length == 0) {
            return null;
        }
        String fieldContent;
        final String substring = lineContent.substring(startIndex, startIndex + length);
        if (this.alignment == FieldAlignment.LEFT) {
            fieldContent = stripStart(substring, paddingChar);
        } else {
            fieldContent = stripEnd(substring, paddingChar);
        }
        return ObjectCreator.buildObject(this.fieldClass, new Object[]{fieldContent});
    }
}
