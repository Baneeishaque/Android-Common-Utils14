package ndk.utils_android1;

public abstract class ApiUtilsWithFileExtension1 extends ApiUtils1{

    @Override
    public String getApiMethodEndpointUrl(String apiMethodName) {

        return getAddressProtocol() + "://" + getServerAddress() + urlSeparator + getServerApplicationFolder() + urlSeparator + apiMethodName + "." + getFileExtension();
    }

    protected abstract String getFileExtension();
}
