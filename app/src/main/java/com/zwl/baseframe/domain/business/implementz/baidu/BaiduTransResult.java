package com.zwl.baseframe.domain.business.implementz.baidu;

import java.util.List;

/**
 * Created by hasee on 2017/8/5.
 */

public class BaiduTransResult {
    //  {"from":"en","to":"zh","trans_result":[{"src":"hello","dst":"\u4f60\u597d"}]}

    private String from;
    private String to;
    private List<TransResult> trans_result;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<TransResult> getTrans_result() {
        return trans_result;
    }

    public void setTrans_result(List<TransResult> trans_result) {
        this.trans_result = trans_result;
    }

    public static class TransResult{
        private String src;
        private String dst;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getDst() {
            return dst;
        }

        public void setDst(String dst) {
            this.dst = dst;
        }
    }
}
