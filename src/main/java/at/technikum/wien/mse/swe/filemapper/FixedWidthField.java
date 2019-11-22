package at.technikum.wien.mse.swe.filemapper;

import static org.apache.commons.lang.StringUtils.stripStart;

class FixedWidthField<T> extends Field<T> {
    private final int startIndex;
    private final int length;
    private final String paddingChar;
    private final FixedWidthFile file;

    FixedWidthField(FileMapper mapper, FieldConverter<T> converter, FixedWidthFile file, int startIndex, int length, String paddingChar) {
        super(mapper, converter);
        this.startIndex = startIndex;
        this.length = length;
        this.paddingChar = paddingChar;
        this.file = file;
    }

    @Override
    public T getValue() {
        String fieldContent = extract(file.getContent());
        return this.converter.convert(fieldContent, mapper);
    }

    private String extract(String content) {
        if (length == 0) {
            return "";
        }
        final String substring = content.substring(startIndex, startIndex + length);
        if (paddingChar == null) {
            return substring.trim();
        } else {
            return stripStart(substring, paddingChar);
        }
    }
}
