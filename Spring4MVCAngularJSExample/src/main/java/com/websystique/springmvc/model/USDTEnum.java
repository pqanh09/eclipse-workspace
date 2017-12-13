package com.websystique.springmvc.model;

public class USDTEnum {
    public final String id;
    public final String name;
    
    public static final USDTEnum USDT_BTC = new USDTEnum("USDT-BTC", "Bitcoin");
    public static final USDTEnum USDT_LTC = new USDTEnum("USDT-LTC", "Litecoin");
    public static final USDTEnum USDT_ETH = new USDTEnum("USDT-ETH", "Ethereum");
    
    public static final USDTEnum USDT_XRP = new USDTEnum("USDT-XRP", "Ripple");
    public static final USDTEnum USDT_BCC = new USDTEnum("USDT-BCC", "Bitcoin Cash");
    public static final USDTEnum USDT_ETC = new USDTEnum("USDT-ETC", "Ethereum Classic");
    
    public static final USDTEnum USDT_NEO = new USDTEnum("USDT-NEO", "Neo");
    public static final USDTEnum USDT_BTG = new USDTEnum("USDT-BTG", "Bitcoin Gold");
    public static final USDTEnum USDT_OMG = new USDTEnum("USDT-OMG", "OmiseGo");
    
    public static final USDTEnum USDT_ZEC = new USDTEnum("USDT-ZEC", "ZCash");
    public static final USDTEnum USDT_DASH = new USDTEnum("USDT-DASH", "Dash");
    public static final USDTEnum USDT_XMR = new USDTEnum("USDT-XMR", "Monero");
    
    public static final USDTEnum USDT_UKN = new USDTEnum("UNKNOWN", "Unkown");
    
//    USDT-BTC	Bitcoin
//    USDT-BCC	Bitcoin Cash
//    USDT-BTG	Bitcoin Gold
//    USDT-DASH	Dash
//    USDT-ETH	Ethereum
//    USDT-ETC	Ethereum Classic
//    USDT-LTC	Litecoin
//    USDT-XMR	Monero
//    USDT-NEO	Neo
//    USDT-OMG	OmiseGo
//    USDT-XRP	Ripple
//    USDT-ZEC	ZCash

	/**
     * Instantiate a Level object.
     */
    private USDTEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public String toString() {
        return id;
    }
    
    public String getName() {
        return name;
    }
}
