package com.sm.portal.constants;

public class WebDavServerConstant {

	//@Value("${WEBDAV_URL}")
	public static String WEBDAV_SERVER_URL="http://localhost:8080/webdav";
	//public static String WEBDAV_SERVER_URL="http://13.127.5.53:8080/webdav";
	public static final String HTML_IMAGE_TAG = "<img src=\"{0}\"  width=\"100\" height=\"120\">";
	public static final String HTML_VIDEO_TAG = "<video  height='134' controls> <source src=\"{0}\" type='video/mp4' style='height: 134px;'/></video>";
	public static final String HTML_AUDIO_TAG ="<audio controls> <source src=\"{0}\" type='audio/mp3'></audio>";
	public static final String MEDIA_URL ="/sm/media";
	public static final String MEDIA_URL2 ="/sm/getFileBankMedia";
}
