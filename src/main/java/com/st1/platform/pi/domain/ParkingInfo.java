package com.st1.platform.pi.domain;

import com.st1.platform.all.domain.UserInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

//Pi 1.1 : ParkingInfo 도메인클래스 생성 및 작성.
@Getter
@ToString(callSuper = true)
@Table(indexes={
        @Index(columnList = "localCode"),
        @Index(columnList = "parkingType")
})
@Entity
public class ParkingInfo {

    //Pi 1.2 : id(게시글 고유No)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parkNo; // 주차장 분류코드

    //Pi 1.3 : 회원정보 연결
    @Setter
    @JoinColumn(name = "userId") // 회원정보 테이블 연결
    @ManyToOne(optional = false)
    private UserInfo userInfo; //유저 정보 (ID)

    //Pi 1.4 : 나머지 컬럼들 연결
    @Setter @Column(nullable = false) private String localCode; // 지역코드
    @Setter @Column(nullable = false) private String parkingName; // 현장명
    @Setter @Column(nullable = false) private String tagName; // 약칭명
    @Setter @Column(nullable = false) private String contractCompany; //계약업체
    @Setter @Column(nullable = false) private int orderCompany; // 수주업체  0:에스티원, 1:AI테크
    @Setter @Column(nullable = false) private String parkingAddr; // 현장 상세주소
    @Setter @Column(nullable = false) private int parkingType; // 주차장 유형
    @Setter @Column(nullable = false) private LocalDateTime serviceDate; // 유지보수기간
    @Setter @Column(nullable = false) private String parkContent; // 메모
    @Setter @Column(nullable = false) private LocalDateTime contractedAt; // 계약일시

    //Pi 1.5
    protected ParkingInfo(){}

    // Pi 1.6 :  (Cmd+n -> Constructor)
    public ParkingInfo(UserInfo userInfo, String localCode, String parkingName, String tagName, String contractCompany, int orderCompany, String parkingAddr, int parkingType, LocalDateTime serviceDate, String parkContent, LocalDateTime contractedAt) {
        this.userInfo = userInfo;
        this.localCode = localCode;
        this.parkingName = parkingName;
        this.tagName = tagName;
        this.contractCompany = contractCompany;
        this.orderCompany = orderCompany;
        this.parkingAddr = parkingAddr;
        this.parkingType = parkingType;
        this.serviceDate = serviceDate;
        this.parkContent = parkContent;
        this.contractedAt = contractedAt;
    }

    // Pi 1.7
    public static ParkingInfo of(UserInfo userInfo, String localCode, String parkingName, String tagName, String contractCompany, int orderCompany, String parkingAddr, int parkingType, LocalDateTime serviceDate, String parkContent, LocalDateTime contractedAt){
        return new ParkingInfo(userInfo, localCode, parkingName, tagName, contractCompany, orderCompany, parkingAddr, parkingType, serviceDate, parkContent, contractedAt);
    }

    // Pi 1.8  (Cmd+n -> equals() and hashCode()) 뼈대(skeleton code) 작성
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkingInfo that)) return false;
        return this.getParkNo() != null && this.getParkNo().equals(that.getParkNo());
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.getParkNo());
    }
}
