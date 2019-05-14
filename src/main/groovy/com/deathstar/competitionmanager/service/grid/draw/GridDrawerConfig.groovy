package com.deathstar.competitionmanager.service.grid.draw

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

import java.awt.Color

@ConfigurationProperties(prefix = 'cm.grid.drawer')
@Configuration
class GridDrawerConfig {

    int titlePenSize = 30
    int titleTab = 10
    Color titlePenColor = Color.black

    int titleWidthTab = titleTab
    int titleHeightTab = titlePenSize + titleTab

    int listWidth = 1600
    int listHeight = 900

    int sectionWidth = 200
    int sectionHeight = 60
    Color sectionPenColor = Color.BLACK
    int sectionPenSize = 15

    int space = 60
    int heightSpace = 100
    int lineWidth = 40
}
