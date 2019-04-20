package com.lz.toy.config;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@Data
public class PushEntity {
    /**
     * appkey : xx
     * timestamp : xx
     * type : broadcast
     * payload : {"display_type":"notification","body":{"ticker":"xx","title":"xx","text":"xx","after_open":"go_app"}}
     * policy : {"start_time":"xx","expire_time":"xx"}
     * production_mode : false
     */

    private String appkey;
    private String timestamp;
    private String type;
    private String alias_type;
    private String alias;
    private PayloadBean payload;
    private PolicyBean policy;
    private String production_mode;
    private String description;

    @Accessors(chain = true)
    @Data
    public static class PayloadBean {
        /**
         * display_type : notification
         * body : {"ticker":"xx","title":"xx","text":"xx","after_open":"go_app"}
         */

        private String display_type = "notification";
        private BodyBean body;

        @Accessors(chain = true)
        @Data
        public static class BodyBean {
            /**
             * ticker : xx
             * title : xx
             * text : xx
             * after_open : go_app
             */

            private String ticker;
            private String title;
            private String text;
            private String after_open = "go_app";
        }
    }

    @Accessors(chain = true)
    @Data
    public static class PolicyBean {
        /**
         * start_time : xx
         * expire_time : xx
         */

        private Date start_time;
        private Date expire_time;
    }
}
