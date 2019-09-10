package com.deathstar.competitionmanager.domain

import javax.persistence.*

@Entity
@Table(name = "attachment")
class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = 'id', nullable = false)
    Integer id

    @Column(name = 'competition_id', nullable = false)
    Integer competitionId

    @Column(name = 'attachment_name', nullable = false)
    String attachmentName

    @Column(name = 'content_link', nullable = false)
    String contentLink
}
