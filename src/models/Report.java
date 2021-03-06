package models;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "reports")
@NamedQueries({
    @NamedQuery(
        name = "getAllReports",
        query = "SELECT r FROM Report AS r ORDER BY r.id DESC"
    ),
    @NamedQuery(
        name = "getReportsCount",
        query = "SELECT COUNT(r) FROM Report AS r"
    ),
    @NamedQuery(
        name = "getMyAllReports",
        query = "SELECT r FROM Report AS r WHERE r.employee = :employee ORDER BY r.id DESC"
    ),
    @NamedQuery(
        name = "getMyReportsCount",
        query = "SELECT COUNT(r) FROM Report AS r WHERE r.employee = :employee"
    )
})
@Entity
public class Report {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "report_date", nullable = false)
    private Date report_date;

    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    @Column(name = "arrived_at", nullable = true)
    private Time arrived_at;

    @Column(name = "leaved_at", nullable = true)
    private Time leaved_at;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = true)
    private Company company;

    @Lob
    @Column(name = "business_mtg", nullable = true)
    private String business_mtg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getReport_date() {
        return report_date;
    }

    public void setReport_date(Date report_date) {
        this.report_date = report_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Time getArrived_at() {
        return arrived_at;
    }

    public void setArrived_at(Time arrived_at) {
        this.arrived_at = arrived_at;
    }

    public Time getLeaved_at() {
        return leaved_at;
    }

    public void setLeaved_at(Time leaved_at) {
        this.leaved_at = leaved_at;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getBusiness_mtg() {
        return business_mtg;
    }

    public void setBusiness_mtg(String business_mtg) {
        this.business_mtg = business_mtg;
    }
}
