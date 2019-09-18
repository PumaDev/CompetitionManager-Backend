package com.deathstar.competitionmanager.domain.mail


import javax.persistence.*

@Entity
@Table(name = 'mail_template')
class MailTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = 'id', nullable = false)
    Integer id

    @Column(name = 'key_name', nullable = false)
    String keyName

    @Column(name = 'template', nullable = false)
    String template

    @OneToMany(cascade = [CascadeType.MERGE], fetch = FetchType.EAGER, mappedBy = 'mailTemplate')
    Set<MailTemplateReplacment> mailTemplateReplacements = new HashSet<>()
}
