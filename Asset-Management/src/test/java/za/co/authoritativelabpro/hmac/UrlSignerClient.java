package za.co.authoritativelabpro.hmac;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import java.net.URL;

public class UrlSignerClient {

	private static byte[] key;
	private static final Base64 base64 = new Base64();

	public UrlSignerClient() {
	}

	public String makeRESRCall(String inputUrl, String clientId, String inputKey) throws IOException, InvalidKeyException, NoSuchAlgorithmException, URISyntaxException {

		String rawUrl = inputUrl + "?clientId=" + clientId;

		URL url = new URL(rawUrl);
		UrlSignerClient signer = new UrlSignerClient(inputKey);
		String request = signer.signRequest(url.getPath(), url.getQuery());

		return url.getProtocol() + "://" + url.getHost() + ":" + url.getPort()
				+ request;
	}

	public UrlSignerClient(String keyString) throws IOException {
		// Convert the key from 'web safe' base 64 to binary
		keyString = keyString.replace('-', '+');
		keyString = keyString.replace('_', '/');

		UrlSignerClient.key = base64.decode(keyString);
	}

	public String signRequest(String path, String query)
			throws NoSuchAlgorithmException, InvalidKeyException,
			UnsupportedEncodingException, URISyntaxException {

		// Retrieve the proper URL components to sign
		String resource = path + '?' + query;

		// Get a HMAC-SHA1 signing key from the raw key bytes
		SecretKeySpec sha1Key = new SecretKeySpec(key, "HmacSHA1");

		// Get a HMAC-SHA1 Mac instance and initialize it with the HMAC-SHA1 key
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(sha1Key);

		// compute the binary signature for the request
		byte[] sigBytes = mac.doFinal(resource.getBytes());

		// base 64 encode the binary signature
		String signature = base64.encodeToString(sigBytes);
		
		// convert the signature to 'web safe' base 64
		signature = signature.replace('+', '-');
		signature = signature.replace('/', '_');

		String encodedSignature = URLEncoder.encode(signature, "UTF-8");
		
		return resource + "&signature=" + encodedSignature;
	}
}