package org.project.helper;

import java.util.ArrayList;

/**
 * Created by wiesen on 16-8-29.
 */
public class HanziHelper {

    public static String hanziToPinyinByFirst(String content){
        ArrayList<HanziToPinyin.Token> tokens = HanziToPinyin.getInstance().get(content);
        StringBuilder sb = new StringBuilder();
        if (tokens != null && tokens.size() > 0) {
            for (HanziToPinyin.Token token : tokens) {
                if (token.type == HanziToPinyin.Token.PINYIN) {
                    sb.append(token.target);
                } else {
                    sb.append(token.source);
                }
            }
        }
        return sb.substring(0,1).toUpperCase();
    }

}
