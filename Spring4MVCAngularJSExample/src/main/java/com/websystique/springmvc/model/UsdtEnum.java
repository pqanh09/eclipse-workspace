package com.websystique.springmvc.model;

public class UsdtEnum {
	public final int number;
    public final String id;
    public final String name;
    
    public static UsdtEnum toEnum(int x){
    	switch (x) {
		case 0:
			return USDT_BTC;
		case 1:
			return USDT_BCC;
		case 2:
			return USDT_BTG;

		case 3:
			return USDT_DASH;
		case 4:
			return USDT_ETH;
		case 5:
			return USDT_ETC;
			
		case 6:
			return USDT_LTC;
		case 7:
			return USDT_XMR;
		case 8:
			return USDT_NEO;
			
		case 9:
			return USDT_OMG;
		case 10:
			return USDT_XRP;
		case 11:
			return USDT_ZEC;
		default:
			return USDT_UKN;
		}
    }
    public static final UsdtEnum USDT_BTC = new UsdtEnum(0, "USDT-BTC", "Bitcoin");
    public static final UsdtEnum USDT_BCC = new UsdtEnum(1, "USDT-BCC", "Bitcoin Cash");
    public static final UsdtEnum USDT_BTG = new UsdtEnum(2, "USDT-BTG", "Bitcoin Gold");
    
    public static final UsdtEnum USDT_DASH = new UsdtEnum(3, "USDT-DASH", "Dash");
    public static final UsdtEnum USDT_ETH = new UsdtEnum(4, "USDT-ETH", "Ethereum");
    public static final UsdtEnum USDT_ETC = new UsdtEnum(5, "USDT-ETC", "Ethereum Classic");

// 0   USDT-BTC	Bitcoin
// 1  USDT-BCC	Bitcoin Cash
// 2   USDT-BTG	Bitcoin Gold
// 3   USDT-DASH	Dash
// 4   USDT-ETH	Ethereum
// 5   USDT-ETC	Ethereum Classic
// 6   USDT-LTC	Litecoin
// 7   USDT-XMR	Monero
// 8   USDT-NEO	Neo
// 9   USDT-OMG	OmiseGo
// 10   USDT-XRP	Ripple
// 11   USDT-ZEC	ZCash
    public static final UsdtEnum USDT_LTC = new UsdtEnum(6, "USDT-LTC", "Litecoin");
    public static final UsdtEnum USDT_XMR = new UsdtEnum(7, "USDT-XMR", "Monero");
    public static final UsdtEnum USDT_NEO = new UsdtEnum(8, "USDT-NEO", "Neo");
    
    public static final UsdtEnum USDT_OMG = new UsdtEnum(9, "USDT-OMG", "OmiseGo");
    public static final UsdtEnum USDT_XRP = new UsdtEnum(10, "USDT-XRP", "Ripple");
    public static final UsdtEnum USDT_ZEC = new UsdtEnum(11, "USDT-ZEC", "ZCash");
    
    public static final UsdtEnum USDT_UKN = new UsdtEnum(12, "UNKNOWN", "Unkown");
    

	/**
     * Instantiate a Level object.
     */
    private UsdtEnum(int number, String id, String name) {
        this.number = number;
        this.id = id;
        this.name = name;
    }
    
    public String toString() {
        return name;
    }
    
    public String toId() {
        return id;
    }
    
    public int toNum() {
        return number;
    }
}
