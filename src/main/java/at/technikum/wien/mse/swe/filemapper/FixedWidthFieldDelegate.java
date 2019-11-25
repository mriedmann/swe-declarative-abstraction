package at.technikum.wien.mse.swe.filemapper;

import at.technikum.wien.mse.swe.filemapper.annotations.FieldAlignment;

import static org.apache.commons.lang.StringUtils.stripEnd;
import static org.apache.commons.lang.StringUtils.stripStart;

class FixedWidthFieldDelegate<T> extends FieldDelegate<T> {
    private final int startIndex;
    private final int length;
    private final String paddingChar;
    private final FixedWidthFile file;
    private final FieldAlignment alignment;

    FixedWidthFieldDelegate(Class<T> fieldClass, String fieldName, FixedWidthFile file, int startIndex, int length, String padding, FieldAlignment alignment) {
        super(fieldClass, fieldName);
        this.startIndex = startIndex;
        this.length = length;
        this.paddingChar = padding;
        this.file = file;
        this.alignment = alignment;
    }

    @Override
    public T getValue() {
        String fieldContent = extract(file.getContent());
        return ObjectCreator.buildObject(this.fieldClass, new Object[]{fieldContent});
    }

    private String extract(String content) {
        if (length == 0) {
            return "";
        }
        final String substring = content.substring(startIndex, startIndex + length);
        if (this.alignment == FieldAlignment.LEFT) {
            return stripStart(substring, paddingChar);
        } else {
            return stripEnd(substring, paddingChar);
        }
    }
}
