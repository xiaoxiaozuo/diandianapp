package com.example.asus.diandianapp;

import java.util.ArrayList;
import java.util.List;



public class MovieLab {
    private static MovieLab instance = null;
    private List<String> movie;
    private List<String> url;
    private List<String> list;

    private MovieLab() {
        init();
    }

    public static MovieLab get() {
        if (null == instance) {
            instance = new MovieLab();
        }
        return instance;

    }

    public int getSize() {
        return movie.size();

    }

    public String getMovie(int n) {
        return movie.get(n);
    }

    public String getUrl(int n) {
        return url.get(n);
    }

    private void chaifen() {
        int n = 0;

        for (String i : list) {
            String[] tmp = i.split(",");
            movie.add(tmp[0]);
            url.add(tmp[1]);

        }

    }


    private void init() {
        movie = new ArrayList<>();
        url = new ArrayList<>();
        list = new ArrayList<>();
        list.add("四川文化,http://scgctvshow.sctv.com/hdlive/sctv2/3.m3u8");
        list.add("四川经济,http://scgctvshow.sctv.com/hdlive/sctv3/3.m3u8");
        list.add("CCTV1,http://223.110.245.159/ott.js.chinamobile.com/PLTV/3/224/3221225852/index.m3u8");
        list.add("CCTV3,http://223.110.245.147/ott.js.chinamobile.com/PLTV/3/224/3221226992/index.m3u8");
        list.add("峨眉电影,http://scgctvshow.sctv.com/hdlive/emei/3.m3u8");
        list.add("CCTV8,http://223.110.245.141/ott.js.chinamobile.com/PLTV/3/224/3221225866/index.m3u8");

        list.add("四川影视,http://scgctvshow.sctv.com/hdlive/sctv5/3.m3u8");









        chaifen();
    }
}
