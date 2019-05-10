package com.deathstar.competitionmanager.service.grid.draw

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@ConfigurationProperties(prefix = 'cm.grid.drawer')
@Configuration
class GridDrawerConfig {

    int titlePenSize = 30
    int titleTab = 10

    int titleWidthTab = titleTab
    int titleHeightTab = titlePenSize + titleTab

    int listWidth = 1600
    int listHeight = 900

    int sectionWidth = 100
    int sectionHeight = 40

    int space = 30
    int lineWidth = 20
}
