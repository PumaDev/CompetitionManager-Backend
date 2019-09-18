package com.deathstar.competitionmanager.domain.mail

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity()
@Table(name = 'mail_template_replacement')
class MailTemplateReplacment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = 'id', nullable = false)
    Integer id

    @Column(name = 'key', nullable = false)
    String key

    @Column(name = 'field_name', nullable = false)
    String fieldName

    @ManyToOne
    @JoinColumn(name='mail_template_id', nullable=false)
    private MailTemplate mailTemplate
}
