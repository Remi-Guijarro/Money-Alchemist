package test.dev.model.encryption;

public interface Crypto {
	String encrypt(String data) throws Exception;
	String decrypt(String data) throws Exception;
}
