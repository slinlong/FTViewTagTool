package com.slinlong.model;

/**
 * @ClassName TagModel
 * @Description SE Tag模型，对应TagTable
 * @Author z2006
 * @Date 2020/8/9 16:08
 * @Version 1.0
 **/
public class TagModel {
    private String tagType;
    private String tagName;
    private String tagDescription;
    private String readOnly;
    private String dataSource;
    private String securityCode;
    private String alarmed;
    private String nativeType;
    private String valueType;
    private String minAnalog;
    private String maxAnalog;
    private String initialAnalog;
    private String scale;
    private String offset;
    private String deadBand;
    private String units;
    private String offLabelDigital;
    private String onLabelDigital;
    private String initialDigital;
    private String lengthString;
    private String initialString;
    private String retentive;
    private String address;
    private String systemSourceName;
    private String systemSourceIndex;
    private String rioAddress;
    private String elementSizeBlock;
    private String numberElementsBlock;
    private String initialBlock;

    public TagModel(AnalogTag analogTag){
        setFieldsNone();

        setTagType("A");
        setTagName(analogTag.getTagName());
        setTagDescription(analogTag.getTagDescription());
        setReadOnly("F");
        setDataSource("D");
        setSecurityCode(analogTag.getSecurityCode());
        setAlarmed("F");
        setNativeType("F");
        setValueType("F");
        setMinAnalog("-100000");
        setMaxAnalog("100000");
        setInitialAnalog("0");
        setScale("1");
        setOffset("0");
        setDeadBand("0");
        setAddress(analogTag.getAddress());
    }

    public TagModel(DigtalTag digtalTag){
        setFieldsNone();

        setTagType("D");
        setTagName(digtalTag.getTagName());
        setTagDescription(digtalTag.getTagDescription());
        setReadOnly("F");
        setDataSource("D");
        setSecurityCode(digtalTag.getSecurityCode());
        setAlarmed("F");
        setOffLabelDigital("Off");
        setOnLabelDigital("On");
        setInitialDigital("Off");
        setAddress(digtalTag.getAddress());
    }

    public TagModel(StringTag stringTag){
        setFieldsNone();
        setTagType("S");
        setTagName(stringTag.getTagName());
        setTagDescription(stringTag.getTagDescription());
        setReadOnly("F");
        setDataSource("M");
        setSecurityCode(stringTag.getSecurityCode());
        setAlarmed("F");
        setLengthString("82");
        setInitialString(stringTag.getTagDescription());
        setRetentive("0");

    }

    public TagModel(TagFolder tagFolder){
        setFieldsNone();

        setTagType("F");
        setTagName(tagFolder.getFolderName());
        setReadOnly("F");
    }

    public TagModel(int flag){
        switch (flag){
            case 0:
                //字段行
                setFields();
                break;
            case -1:
                //警示提示行
                setFieldsNone();
                setTagType(";###002 - THIS LINE CONTAINS VERSION INFORMATION. DO NOT REMOVE!!!");
                break;
            case 1:
                //文件夹标题行
                setFieldsNone();
                setTagType(";Folders Section (Must define folders before tags)");
                break;
            case 2:
                //标签标题行
                setFieldsNone();
                setTagType(";Tag Section");
                break;
            default:
                //空行
                setFieldsNone();
        }
    }

    private void setFields(){
        setTagType(";Tag Type");
        setTagName("Tag Name");
        setTagDescription("Tag Description");
        setReadOnly("Read Only");
        setDataSource("Data Source");
        setSecurityCode("Security Code");
        setAlarmed("Alarmed");
        setNativeType("Native Type");
        setValueType("Value Type");
        setMinAnalog("Min Analog");
        setMaxAnalog("Max Analog");
        setInitialAnalog("Initial Analog");
        setScale("Scale");
        setOffset("Offset");
        setDeadBand("DeadBand");
        setUnits("Units");
        setOffLabelDigital("Off Label Digital");
        setOnLabelDigital("On Label Digital");
        setInitialDigital("Initial Digital");
        setLengthString("Length String");
        setInitialString("Initial String");
        setRetentive("Retentive");
        setAddress("Address");
        setSystemSourceName("System Source Name");
        setSystemSourceIndex("System Source Index");
        setRioAddress("RIO Address");
        setElementSizeBlock("Element Size Block");
        setNumberElementsBlock("Number Elements Block");
        setInitialBlock("Initial Block");
    }

    private void setFieldsNone(){
        setTagType("");
        setTagName("");
        setTagDescription("");
        setReadOnly("");
        setDataSource("");
        setSecurityCode("");
        setAlarmed("");
        setNativeType("");
        setValueType("");
        setMinAnalog("");
        setMaxAnalog("");
        setInitialAnalog("");
        setScale("");
        setOffset("");
        setDeadBand("");
        setUnits("");
        setOffLabelDigital("");
        setOnLabelDigital("");
        setInitialDigital("");
        setLengthString("");
        setInitialString("");
        setRetentive("");
        setAddress("");
        setSystemSourceName("");
        setSystemSourceIndex("");
        setRioAddress("");
        setElementSizeBlock("");
        setNumberElementsBlock("");
        setInitialBlock("");
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagDescription() {
        return tagDescription;
    }

    public void setTagDescription(String tagDescription) {
        this.tagDescription = tagDescription;
    }

    public String getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(String readOnly) {
        this.readOnly = readOnly;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getAlarmed() {
        return alarmed;
    }

    public void setAlarmed(String alarmed) {
        this.alarmed = alarmed;
    }

    public String getNativeType() {
        return nativeType;
    }

    public void setNativeType(String nativeType) {
        this.nativeType = nativeType;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getMinAnalog() {
        return minAnalog;
    }

    public void setMinAnalog(String minAnalog) {
        this.minAnalog = minAnalog;
    }

    public String getMaxAnalog() {
        return maxAnalog;
    }

    public void setMaxAnalog(String maxAnalog) {
        this.maxAnalog = maxAnalog;
    }

    public String getInitialAnalog() {
        return initialAnalog;
    }

    public void setInitialAnalog(String initialAnalog) {
        this.initialAnalog = initialAnalog;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getDeadBand() {
        return deadBand;
    }

    public void setDeadBand(String deadBand) {
        this.deadBand = deadBand;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getOffLabelDigital() {
        return offLabelDigital;
    }

    public void setOffLabelDigital(String offLabelDigital) {
        this.offLabelDigital = offLabelDigital;
    }

    public String getOnLabelDigital() {
        return onLabelDigital;
    }

    public void setOnLabelDigital(String onLabelDigital) {
        this.onLabelDigital = onLabelDigital;
    }

    public String getInitialDigital() {
        return initialDigital;
    }

    public void setInitialDigital(String initialDigital) {
        this.initialDigital = initialDigital;
    }

    public String getLengthString() {
        return lengthString;
    }

    public void setLengthString(String lengthString) {
        this.lengthString = lengthString;
    }

    public String getInitialString() {
        return initialString;
    }

    public void setInitialString(String initialString) {
        this.initialString = initialString;
    }

    public String getRetentive() {
        return retentive;
    }

    public void setRetentive(String retentive) {
        this.retentive = retentive;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSystemSourceName() {
        return systemSourceName;
    }

    public void setSystemSourceName(String systemSourceName) {
        this.systemSourceName = systemSourceName;
    }

    public String getSystemSourceIndex() {
        return systemSourceIndex;
    }

    public void setSystemSourceIndex(String systemSourceIndex) {
        this.systemSourceIndex = systemSourceIndex;
    }

    public String getRioAddress() {
        return rioAddress;
    }

    public void setRioAddress(String rioAddress) {
        this.rioAddress = rioAddress;
    }

    public String getElementSizeBlock() {
        return elementSizeBlock;
    }

    public void setElementSizeBlock(String elementSizeBlock) {
        this.elementSizeBlock = elementSizeBlock;
    }

    public String getNumberElementsBlock() {
        return numberElementsBlock;
    }

    public void setNumberElementsBlock(String numberElementsBlock) {
        this.numberElementsBlock = numberElementsBlock;
    }

    public String getInitialBlock() {
        return initialBlock;
    }

    public void setInitialBlock(String initialBlock) {
        this.initialBlock = initialBlock;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(tagType);
        sb.append(",").append(tagName);
        sb.append(",").append(tagDescription);
        sb.append(",").append(readOnly);
        sb.append(",").append(dataSource);
        sb.append(",").append(securityCode);
        sb.append(",").append(alarmed);
        sb.append(",").append(nativeType);
        sb.append(",").append(valueType);
        sb.append(",").append(minAnalog);
        sb.append(",").append(maxAnalog);
        sb.append(",").append(initialAnalog);
        sb.append(",").append(scale);
        sb.append(",").append(offset);
        sb.append(",").append(deadBand);
        sb.append(",").append(units);
        sb.append(",").append(offLabelDigital);
        sb.append(",").append(onLabelDigital);
        sb.append(",").append(initialDigital);
        sb.append(",").append(lengthString);
        sb.append(",").append(initialString);
        sb.append(",").append(retentive);
        sb.append(",").append(address);
        sb.append(",").append(systemSourceName);
        sb.append(",").append(systemSourceIndex);
        sb.append(",").append(rioAddress);
        sb.append(",").append(elementSizeBlock);
        sb.append(",").append(numberElementsBlock);
        sb.append(",").append(initialBlock);
        return sb.toString();
    }
}
