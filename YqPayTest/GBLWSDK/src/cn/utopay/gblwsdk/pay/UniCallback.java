package cn.utopay.gblwsdk.pay;

public interface UniCallback {

	 public void paySuccess();

	public void payFailed(Exception e);
}
