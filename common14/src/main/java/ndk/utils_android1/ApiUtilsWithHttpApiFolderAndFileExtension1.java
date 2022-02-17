package ndk.utils_android1;

public abstract class ApiUtilsWithHttpApiFolderAndFileExtension1 extends ApiUtilsWithFileExtension1 {

    @Override
    public String getApiMethodEndpointUrl(String apiMethodName) {

        return getAddressProtocol() + "://" + getServerAddress() + urlSeparator + getServerApplicationFolder() + urlSeparator + getServerApplicationHttpApiFolder() + urlSeparator + apiMethodName + getFileExtension();
    }

    public String getImageUrl(String imageFolderUnderServerApplicationFolder, String imageNameWithExtension) {

        return getAddressProtocol() + "://" + getServerAddress() + urlSeparator + getServerApplicationFolder() + urlSeparator + imageFolderUnderServerApplicationFolder + urlSeparator + imageNameWithExtension;
    }

    public String getPngImageUrl(String imageFolderUnderServerApplicationFolder, String imageName) {

        return getImageUrl(imageFolderUnderServerApplicationFolder, imageName + ".png");
    }

    protected abstract String getServerApplicationHttpApiFolder();
}
