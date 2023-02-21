package com.st1.platform.domain;

//import java.time.LocalDateTime; // AuditingFields 로 옮겨짐.
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

//5.1 : Article.class를 참고하여 밑에 내용을 추가해준다.
@Getter
@ToString/* Todo: 나중에 추가됨*/(callSuper = true)
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
//6.2 : Entity에서도 Auditing을 사용한다.
//7.17 : 해당 컬럼을 추출해서 AuditingFields로 옮김
//@EntityListeners(AuditingEntityListener.class)
@Entity
//2.10 : Article 클래스 생성 및 작성
public class ArticleComment /*7.18 : AuditingFields에 내용을 가져와 연결*/  extends AuditingFields {

    //5.2 : Article.class를 참고하여 밑에 내용을 추가해준다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //2.11 : id(댓글 고유No)
    private Long id;

    //5.3 : Article.class를 참고하여 밑에 내용을 추가해준다.
    @Setter
    //5.4 : 연관관계 맵핑
    @ManyToOne(optional = false)
    //2.12 : article_id (게시글 고유No) 연결
    private Article article;

    @Setter
    @JoinColumn(name = "userId")
    @ManyToOne(optional = false)
    private UserInfo userInfo; // 유저 정보 (ID)

    @Setter
    @Column(updatable = false)
    private Long parentCommentId; // 부모 댓글 ID

    @ToString.Exclude
    @OrderBy("createdAt ASC")
    @OneToMany(mappedBy = "parentCommentId", cascade = CascadeType.ALL)
    private Set<ArticleComment> childComments = new LinkedHashSet<>();

    //5.5 : Article.class를 참고하여 밑에 내용을 추가해준다.
    @Setter
    @Column(nullable = false, length = 255)
    //2.13 : content (본문) 연결
    private String content;

    //2.14 : Meta data -- 글 생성일, 생성자, 수정일, 수정자
    //5.6 : (2.14)부분 전체를 Article.class를 참고하여 수정해준다.
    //7.16 : 해당 컬럼을 추출해서 AuditingFields로 옮김
    //@CreatedDate @Column(nullable = false) private LocalDateTime createdAt;
    //@CreatedBy @Column(nullable = false, length = 100) private String createdBy;
    //@LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt;
    //@LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy;

    //5.7 : Article.class를 참고하여 밑에 내용을 추가
    protected ArticleComment() {
    }

    //5.8 : Article.class를 참고하여 밑에 내용을 추가
//  private ArticleComment(Article article, String content) {
//      this.article = article;
//      this.content = content;
//  }
    //Todo : (5.8)부분은 나중에 이렇게 변경된다.
    private ArticleComment(Article article, UserInfo userInfo, Long parentCommentId, String content) {
        this.article = article;
        this.userInfo = userInfo;
        this.parentCommentId = parentCommentId;
        this.content = content;
    }

    // 5.9 : Article.class를 참고하여 밑에 내용을 추가
//  public static ArticleComment(Article article, String content) {
//       return new ArticleComment(article, content);
//  }
    //Todo : (5.9)부분은 나중에 이렇게 변경된다.
    public static ArticleComment of(Article article, UserInfo userInfo, String content) {
        return new ArticleComment(article, userInfo, null, content);
    }

    //Todo: 나중에 추가될 예정
    public void addChildComment(ArticleComment child) {
        child.setParentCommentId(this.getId());
        this.getChildComments().add(child);
    }

    //5.10 :  (Cmd+n -> equals() and hashCode()) 뼈대(skeleton code) 작성
    //        (Objects로추천되있는것 사용)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        //5.11 : 자동 생성되는 부분을 pattern variable(java14이후) 기술을 사용하여 변경
        //if (!(o instanceof ArticleComment that)) return false;
        //return id != null && id.equals(that.id); //모든 새로 만든 Entity들을 각각 개별로 보고 id를 제외한 모든 Entity데이타가 같더라도 id값이 다르다면 서로 다른것으로 본다.
        //Todo: (5.11)부분이 나중에 밑처럼 바뀐다.
        if (!(o instanceof ArticleComment that)) return false;
        return this.getId() != null && this.getId().equals(that.getId());
    }
    @Override
    public int hashCode() {
        //자동 생성되는 부분
        //return Objects.hash(id); //이 동일성 검사에서 hashCode는 이 id만 가지고 hashing한다.
        return Objects.hash(this.getId());
    }

}
