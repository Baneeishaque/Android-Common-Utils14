package ndk.utils_android1;

public abstract class ApiUtils1 {

    public static final String urlSeparator = "/";

    public String getApiMethodEndpointUrl(String apiMethodName) {

        return getAddressProtocol() + "://" + getServerAddress() + urlSeparator + getServerApplicationFolder() + urlSeparator + apiMethodName;
    }

    public String getApiMethodInSectionEndpointUrl(String sectionName, String apiMethodName) {

        return getApiMethodEndpointUrl(sectionName + urlSeparator + apiMethodName);
    }

    public String getApiMethodInSubSectionEndpointUrl(String sectionName, String subSectionName, String apiMethodName) {

        return getApiMethodInSectionEndpointUrl(sectionName + urlSeparator + subSectionName,apiMethodName);
    }

    protected abstract String getServerApplicationFolder();

    protected abstract String getServerAddress();

    protected abstract String getAddressProtocol();
}
