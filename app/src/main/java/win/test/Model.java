package win.test;

import java.io.Serializable;

/**
 * 作者：文庆 on 2016/12/19 20:53
 * 说明：
 */
public class Model implements Serializable{
    int photo;
    String name;
    String introduce;

    public Model(int photo, String name, String introduce) {
        this.photo = photo;
        this.name = name;
        this.introduce = introduce;
    }
}
