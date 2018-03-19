/**
 * StoreWSSoapServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package soapService;

public class StoreWSSoapServiceLocator extends org.apache.axis.client.Service implements soapService.StoreWSSoapService {

    public StoreWSSoapServiceLocator() {
    }


    public StoreWSSoapServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public StoreWSSoapServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for StoreWSSoap
    private java.lang.String StoreWSSoap_address = "http://localhost:8080/EECSProject/services/StoreWSSoap";

    public java.lang.String getStoreWSSoapAddress() {
        return StoreWSSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String StoreWSSoapWSDDServiceName = "StoreWSSoap";

    public java.lang.String getStoreWSSoapWSDDServiceName() {
        return StoreWSSoapWSDDServiceName;
    }

    public void setStoreWSSoapWSDDServiceName(java.lang.String name) {
        StoreWSSoapWSDDServiceName = name;
    }

    public soapService.StoreWSSoap getStoreWSSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(StoreWSSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getStoreWSSoap(endpoint);
    }

    public soapService.StoreWSSoap getStoreWSSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            soapService.StoreWSSoapSoapBindingStub _stub = new soapService.StoreWSSoapSoapBindingStub(portAddress, this);
            _stub.setPortName(getStoreWSSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setStoreWSSoapEndpointAddress(java.lang.String address) {
        StoreWSSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (soapService.StoreWSSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                soapService.StoreWSSoapSoapBindingStub _stub = new soapService.StoreWSSoapSoapBindingStub(new java.net.URL(StoreWSSoap_address), this);
                _stub.setPortName(getStoreWSSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("StoreWSSoap".equals(inputPortName)) {
            return getStoreWSSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://soapService", "StoreWSSoapService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://soapService", "StoreWSSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("StoreWSSoap".equals(portName)) {
            setStoreWSSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
