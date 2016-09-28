package com.iamsj.core.log;

public class LogData {

    public static final String LOG_DATA_C_NAME = "c";

    // 日志规范类型
    private String t;
    // 日志规范版本
    private String v = "1.0";
    // 用户id
    private String u;
    // cookie唯一标识
    private String c;
    // 客户端UTC时间
    private String tc;
    // 服务端UTC时间
    private String ts;
    // 服务端UTC时间（结束）
    private String ts2;
    // User-Agent
    private String ua;
    // Referer
    private String r;
    // 操作系统
    private String os;
    // private String br;
    // 移动设备国际身份码
    private String imei;
    // iOS广告标示符
    private String idfa;
    // MAC地址
    private String mac;
    // 屏幕分辨率
    private String res;
    // IP
    private String ip;
    // 网络类型(UMTS/TD-LTE/Wi-Fi 等)
    private String nt;
    // 集成电路卡识别码
    private String iccid;
    // 国际移动用户识别码
    private String imsi;
    // 产品名称
    private String pd;
    // 产品属性(android/iphone/ipad/wp/web)
    private String pdt;
    // 产品版本(大小版本号)
    private String pdv;
    // 请求协议(TCP/HTTP/HTTPS)
    private String prt;
    // 服务器地址(域名/IP)
    private String svr;
    // 请求端口
    private String pt;
    // HTTP 请求路径
    private String sapi;
    // 客户端产品路径(页面、窗口、控件)
    private String uipos;
    // 状态类
    private String st;
    // 浏览类行为
    private String ac;
    // 增删改操作
    private String op;
    // 对 st/ac/op 的补充信息
    private String inf;
    // 服务端扩展字段
    private String exts;
    // 客户端扩展字段
    private String extc;

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNt() {
        return nt;
    }

    public void setNt(String nt) {
        this.nt = nt;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getPd() {
        return pd;
    }

    public void setPd(String pd) {
        this.pd = pd;
    }

    public String getPdt() {
        return pdt;
    }

    public void setPdt(String pdt) {
        this.pdt = pdt;
    }

    public String getPdv() {
        return pdv;
    }

    public void setPdv(String pdv) {
        this.pdv = pdv;
    }

    public String getPrt() {
        return prt;
    }

    public void setPrt(String prt) {
        this.prt = prt;
    }

    public String getSvr() {
        return svr;
    }

    public void setSvr(String svr) {
        this.svr = svr;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }

    public String getSapi() {
        return sapi;
    }

    public void setSapi(String sapi) {
        this.sapi = sapi;
    }

    public String getUipos() {
        return uipos;
    }

    public void setUipos(String uipos) {
        this.uipos = uipos;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getInf() {
        return inf;
    }

    public void setInf(String inf) {
        this.inf = inf;
    }

    public String getExts() {
        return exts;
    }

    public void setExts(String exts) {
        this.exts = exts;
    }

    public String getExtc() {
        return extc;
    }

    public void setExtc(String extc) {
        this.extc = extc;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public String getTs2() {
        return ts2;
    }

    public void setTs2(String ts2) {
        this.ts2 = ts2;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }
}
