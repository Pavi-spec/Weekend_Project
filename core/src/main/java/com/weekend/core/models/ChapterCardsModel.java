package com.weekend.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
        adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class ChapterCardsModel {

    @SlingObject
    private Resource resource;

    /* =========================================
       CHAPTER
       ========================================= */

    @ValueMapValue
    private String chapterTitle;

    @ValueMapValue
    private String chapterDescription;


    /* =========================================
       STYLE
       ========================================= */

    @ValueMapValue
    private String backgroundColor;

    @ValueMapValue
    private String textColor;

    @ValueMapValue
    private String hoverColor;

    @ValueMapValue
    private Integer borderRadius;


    /* =========================================
       MORE CARD
       ========================================= */

    @ValueMapValue
    private String moreText;

    @ValueMapValue
    private String chapterPagePath;

    @ValueMapValue
    private String moreIcon;


    /* =========================================
       TOPICS
       ========================================= */

    private List<TopicCard> topics;


    /* =========================================
       INITIALIZATION
       ========================================= */

    @PostConstruct
    protected void init() {

        topics = new ArrayList<>();

        Resource topicsResource =
                resource.getChild("topics");

        if (topicsResource != null) {

            for (Resource topicResource
                    : topicsResource.getChildren()) {

                topics.add(
                        new TopicCard(topicResource)
                );
            }
        }


        /* =====================================
           DEFAULT STYLE VALUES
           ===================================== */

        if (backgroundColor == null
                || backgroundColor.trim().isEmpty()) {

            backgroundColor = "#1F2937";
        }

        if (textColor == null
                || textColor.trim().isEmpty()) {

            textColor = "#FFFFFF";
        }

        if (hoverColor == null
                || hoverColor.trim().isEmpty()) {

            hoverColor = "#3B82F6";
        }

        if (borderRadius == null) {

            borderRadius = 12;
        }


        /* =====================================
           DEFAULT MORE TEXT
           ===================================== */

        if (moreText == null
                || moreText.trim().isEmpty()) {

            moreText = "View All Topics";
        }
    }


    /* =========================================
       CHAPTER GETTERS
       ========================================= */

    public String getChapterTitle() {
        return chapterTitle;
    }

    public String getChapterDescription() {
        return chapterDescription;
    }


    public boolean hasDescription() {

        return chapterDescription != null
                && !chapterDescription.trim().isEmpty();
    }


    /* =========================================
       TOPICS
       ========================================= */

    public List<TopicCard> getTopics() {

        return Collections.unmodifiableList(topics);
    }


    public boolean hasTopics() {

        return topics != null
                && !topics.isEmpty();
    }


    /* =========================================
       MORE CARD
       ========================================= */

    public MoreCard getMoreCard() {

        return new MoreCard(
                moreText,
                chapterPagePath,
                moreIcon
        );
    }


    public boolean hasMoreCard() {

        return chapterPagePath != null
                && !chapterPagePath.trim().isEmpty();
    }


    /* =========================================
       STYLE
       ========================================= */

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public String getHoverColor() {
        return hoverColor;
    }

    public Integer getBorderRadius() {
        return borderRadius;
    }


    /* =========================================
       STYLE CSS VARIABLES
       ========================================= */

    public String getStyleString() {

        return "--chapter-bg:"
                + backgroundColor
                + ";"
                + "--chapter-text:"
                + textColor
                + ";"
                + "--chapter-hover:"
                + hoverColor
                + ";"
                + "--chapter-radius:"
                + borderRadius
                + "px;";
    }
}