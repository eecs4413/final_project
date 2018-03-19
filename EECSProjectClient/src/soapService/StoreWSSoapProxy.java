package soapService;

public class StoreWSSoapProxy implements soapService.StoreWSSoap {
  private String _endpoint = null;
  private soapService.StoreWSSoap storeWSSoap = null;
  
  public StoreWSSoapProxy() {
    _initStoreWSSoapProxy();
  }
  
  public StoreWSSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initStoreWSSoapProxy();
  }
  
  private void _initStoreWSSoapProxy() {
    try {
      storeWSSoap = (new soapService.StoreWSSoapServiceLocator()).getStoreWSSoap();
      if (storeWSSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)storeWSSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)storeWSSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (storeWSSoap != null)
      ((javax.xml.rpc.Stub)storeWSSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public soapService.StoreWSSoap getStoreWSSoap() {
    if (storeWSSoap == null)
      _initStoreWSSoapProxy();
    return storeWSSoap;
  }
  
  public java.lang.String getProductInfo(java.lang.String productId) throws java.rmi.RemoteException{
    if (storeWSSoap == null)
      _initStoreWSSoapProxy();
    return storeWSSoap.getProductInfo(productId);
  }
  
  
}