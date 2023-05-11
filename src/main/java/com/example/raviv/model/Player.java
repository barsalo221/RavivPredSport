package com.example.raviv.model;

public class Player {
    private String player;
    private String pos;

    private String age;

    private String tm;

    private String g;

    private String gs;

    private String mp;

    private String fg;

    private String fga;

    private String fgp;

    private String threeP;

    private String threePA;

    private String threePP;

    private String twoP;

    private String twoPA;

    private String twoPP;

    private String efgp;

    private String ft;

    private String fta;

    private String ftp;

    private String orb;

    private String drb;
    private String trb;

    private String ast;

    private String stl;

    private String blk;

    private String tov;

    private String pf;
    private String pts;

    private String tmID;
    private Float ptsOfTm;

    public Player(String player, String pos, String age, String tm, String g, String gs, String mp, String fg, String fga, String fgp, String threeP, String threePA, String threePP, String twoP, String twoPA, String twoPP, String efgp, String ft, String fta, String ftp, String orb, String drb, String trb, String ast, String stl, String blk, String tov, String pf, String pts, String tmID, Float ptsOfTm) {
        this.player = player;
        this.pos = pos;
        this.age = age;
        this.tm = tm;
        this.g = g;
        this.gs = gs;
        this.mp = mp;
        this.fg = fg;
        this.fga = fga;
        this.fgp = fgp;
        this.threeP = threeP;
        this.threePA = threePA;
        this.threePP = threePP;
        this.twoP = twoP;
        this.twoPA = twoPA;
        this.twoPP = twoPP;
        this.efgp = efgp;
        this.ft = ft;
        this.fta = fta;
        this.ftp = ftp;
        this.orb = orb;
        this.drb = drb;
        this.trb = trb;
        this.ast = ast;
        this.stl = stl;
        this.blk = blk;
        this.tov = tov;
        this.pf = pf;
        this.pts = pts;
        this.tmID = tmID;
        this.ptsOfTm = ptsOfTm;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTm() {
        return tm;
    }

    public void setTm(String tm) {
        this.tm = tm;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }

    public String getGs() {
        return gs;
    }

    public void setGs(String gs) {
        this.gs = gs;
    }

    public String getMp() {
        return mp;
    }

    public void setMp(String mp) {
        this.mp = mp;
    }

    public String getFg() {
        return fg;
    }

    public void setFg(String fg) {
        this.fg = fg;
    }

    public String getFga() {
        return fga;
    }

    public void setFga(String fga) {
        this.fga = fga;
    }

    public String getFgp() {
        return fgp;
    }

    public void setFgp(String fgp) {
        this.fgp = fgp;
    }

    public String getThreeP() {
        return threeP;
    }

    public void setThreeP(String threeP) {
        this.threeP = threeP;
    }

    public String getThreePA() {
        return threePA;
    }

    public void setThreePA(String threePA) {
        this.threePA = threePA;
    }

    public String getThreePP() {
        return threePP;
    }

    public void setThreePP(String threePP) {
        this.threePP = threePP;
    }

    public String getTwoP() {
        return twoP;
    }

    public void setTwoP(String twoP) {
        this.twoP = twoP;
    }

    public String getTwoPA() {
        return twoPA;
    }

    public void setTwoPA(String twoPA) {
        this.twoPA = twoPA;
    }

    public String getTwoPP() {
        return twoPP;
    }

    public void setTwoPP(String twoPP) {
        this.twoPP = twoPP;
    }

    public String getEfgp() {
        return efgp;
    }

    public void setEfgp(String efgp) {
        this.efgp = efgp;
    }

    public String getFt() {
        return ft;
    }

    public void setFt(String ft) {
        this.ft = ft;
    }

    public String getFta() {
        return fta;
    }

    public void setFta(String fta) {
        this.fta = fta;
    }

    public String getFtp() {
        return ftp;
    }

    public void setFtp(String ftp) {
        this.ftp = ftp;
    }

    public String getOrb() {
        return orb;
    }

    public void setOrb(String orb) {
        this.orb = orb;
    }

    public String getDrb() {
        return drb;
    }

    public void setDrb(String drb) {
        this.drb = drb;
    }

    public String getTrb() {
        return trb;
    }

    public void setTrb(String trb) {
        this.trb = trb;
    }

    public String getAst() {
        return ast;
    }

    public void setAst(String ast) {
        this.ast = ast;
    }

    public String getStl() {
        return stl;
    }

    public void setStl(String stl) {
        this.stl = stl;
    }

    public String getBlk() {
        return blk;
    }

    public void setBlk(String blk) {
        this.blk = blk;
    }

    public String getTov() {
        return tov;
    }

    public void setTov(String tov) {
        this.tov = tov;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    public String getPts() {
        return pts;
    }

    public void setPts(String pts) {
        this.pts = pts;
    }

    public String getTmID() {
        return tmID;
    }

    public void setTmID(String tmID) {
        this.tmID = tmID;
    }

    public Float getPtsOfTm() {
        return ptsOfTm;
    }

    public void setPtsOfTm(Float ptsOfTm) {
        this.ptsOfTm = ptsOfTm;
    }

    @Override
    public String toString() {
        return "Player{" +
                "player='" + player + '\'' +
                ", pos='" + pos + '\'' +
                ", age='" + age + '\'' +
                ", tm='" + tm + '\'' +
                ", g='" + g + '\'' +
                ", gs='" + gs + '\'' +
                ", mp='" + mp + '\'' +
                ", fg='" + fg + '\'' +
                ", fga='" + fga + '\'' +
                ", fgp='" + fgp + '\'' +
                ", threeP='" + threeP + '\'' +
                ", threePA='" + threePA + '\'' +
                ", threePP='" + threePP + '\'' +
                ", twoP='" + twoP + '\'' +
                ", twoPA='" + twoPA + '\'' +
                ", twoPP='" + twoPP + '\'' +
                ", efgp='" + efgp + '\'' +
                ", ft='" + ft + '\'' +
                ", fta='" + fta + '\'' +
                ", ftp='" + ftp + '\'' +
                ", orb='" + orb + '\'' +
                ", drb='" + drb + '\'' +
                ", trb='" + trb + '\'' +
                ", ast='" + ast + '\'' +
                ", stl='" + stl + '\'' +
                ", blk='" + blk + '\'' +
                ", tov='" + tov + '\'' +
                ", pf='" + pf + '\'' +
                ", pts='" + pts + '\'' +
                ", tmID='" + tmID + '\'' +
                ", ptsOfTm=" + ptsOfTm +
                '}';
    }
}
