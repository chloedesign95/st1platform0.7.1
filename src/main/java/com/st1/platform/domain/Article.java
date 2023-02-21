package com.st1.platform.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
//import java.time.LocalDateTime; // AuditingFields 로 옮겨짐.
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

//4.1 : Lombok 사용 -> 모두 Getter, 모든 빌드는 접근이 가능하다.
@Getter
//4.2 : @Setter : 자동으로 JPA에서 만들어지게 될 요소들은 필드에 @Setter를 붙이지 않는다.
//                고로 전체레벨로 Setter를 잡지 않고 각 필용한 필드에만 개별로 넣는 방법으로 진행.
//4.3 : @ToString : 쉽게 출력이 가능하고 관찰할 수 있게
@ToString/* Todo: 나중에 추가됨*/(callSuper = true)
//4.4 : @Table 인덱스키를 걸어 건 항목으로 검색을 할 수 있다.
@Table(indexes = {//Index Key : 사이즈에 제한이 있으므로 주의.
                   @Index(columnList = "title"),
                   //@Index(columnList = "hashtag"), // Todo: 나중에 빠짐
                   @Index(columnList = "createdAt"),
                   @Index(columnList = "createdBy")
                 }
       )
//6.1 : Entity에서도 Auditing을 사용한다.
//7.8 : AuditingFields로 옮김
// @EntityListeners(AuditingEntityListener.class)
//4.4 : 추가
@Entity//Entity라는것을 명시해준다.
//2.3 : Article 클래스 생성 및 작성
public class Article /*7.15 : AuditingFields에 내용을 가져와 연결*/ extends AuditingFields {

    //4.5 : ID = Primary Key  : 다른 테이블과 Joint 할때 기본값이된다.
    @Id
    //4.6 : 자동으로 AutoIncreament를 걸어준다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)//mysql의 AutoIncreament는 IDENTITY방식으로 만들어진다.
    //2.4 : id(게시글 고유No)
    private Long id; // AS접수 (JPApersistance가 영속성을할때 자동으로 만들어주는 고유번호)

    @Setter
    @JoinColumn(name = "userId") //회원정보 테이블을 연결해준다.
    @ManyToOne(optional = false)
    private UserInfo userInfo; // 유저 정보 (ID)

    //4.7 : 나중에 Domain에서 수정이 가능하도록 @Setter를 붙이고 컬럼NotNull도 지정해준다.
    @Setter @Column(nullable = false)//Column은 기본값이 Null이며, 생략이 가능하다
    //2.5 : title(제목)
    private String title;

    //4.7 : 나중에 Domain에서 수정이 가능하도록 @Setter를 붙이고 컬럼NotNull과 length도 지정해준다.
    @Setter @Column(nullable = false, length = 200)
    //2.6 : content(내용)
    private String content;

    @ToString.Exclude
    @JoinTable(
            name = "article_hashtag",
            joinColumns = @JoinColumn(name = "articleId"),
            inverseJoinColumns = @JoinColumn(name = "hashtagId")
    )
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    //2.7 : Hashtag(해쉬태그)
    //private String hashtags; 를 변형함.
    private Set<Hashtag> hashtags = new LinkedHashSet<>();

    //5.12 : 양방향 바인딩 필드
    @ToString.Exclude
    //@OrderBy("id") //정렬기준 /*Todo : 나중에 밑처럼 바뀐다*/
    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL) //모든경우에서 cascading constraint를 적용하겠다.
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    //2.8 : Meta data -- 글 생성일, 생성자, 수정일, 수정자
    //7.3 : 해당 컬럼을 추출해서 AuditingFields로 옮김
    //@CreatedDate/*4.8 : 를 붙여 Auditing기술을 사용*/ @Column(nullable = false) /*2.8*/ private LocalDateTime createdAt;
    //@CreatedBy/*4.8 : 를 붙여 Auditing기술을 사용*/ @Column(nullable = false, length = 100) /*2.8*/ private String createdBy;
    //@LastModifiedDate/*4.8 : 를 붙여 Auditing기술을 사용*/ @Column(nullable = false)  /*2.8*/ private LocalDateTime modifiedAt;
    //@LastModifiedBy/*4.8 : 를 붙여 Auditing기술을 사용*/ @Column(nullable = false, length = 100)  /*2.8*/ private String modifiedBy;


    //4.16 : 모든 JPA Entity들은 hibernate를 사용하는 기준으로  기본생성자를 가지고있어야한다.
    protected Article() {}/*!.private는 안됨.오류남 */

    //4.17 : (Cmd+n -> Constructor) 이 Domain과 관련있는 정보만 Open하는 방식으로 생성자를 통해 만들 수 있게끔 유도를 한다.
//    private Article(String title, String content, String hashtags) {//private로 막아준다.
//        this.title = title;
//        this.content = content;
//        this.hashtags = hashtags;
//    }
    //Todo : 4.17부분은 나중에 이렇게 변경된다.
    private Article(UserInfo userInfo, String title, String content) {
        this.userInfo = userInfo;
        this.title = title;
        this.content = content;
    }

    // 4.18 : factory 메소드를 통해 (4.17부분)을 제공할 수 있도록 한다.
//  public static Article(String title, String content, String hashtags) {
//       return new Article(title, content, hashtag);
//  }
    //Todo : (4.18)부분은 나중에 이렇게 변경된다.
    public static Article of(UserInfo userInfo, String title, String content) {
        return new Article(userInfo, title, content);
    }

    //Todo: 나중에 추가될 예정
    public void addHashtag(Hashtag hashtag) {
        this.getHashtags().add(hashtag);
    }

    public void addHashtags(Collection<Hashtag> hashtags) {
        this.getHashtags().addAll(hashtags);
    }

    public void clearHashtags() {
        this.getHashtags().clear();
    }

    //4.19 :  (Cmd+n -> equals() and hashCode()) 뼈대(skeleton code) 작성
    //        (Objects로추천되있는것 사용)     id만 체크하여 검사한다 (NN로체크)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        //4.20 : 자동 생성되는 부분을 pattern variable(java14이후) 기술을 사용하여 변경
        //if (!(o instanceof Article article)) return false;
        //return id != null && id.equals(article.id); //모든 새로 만든 Entity들을 각각 개별로 보고 id를 제외한 모든 Entity데이타가 같더라도 id값이 다르다면 서로 다른것으로 본다.
        //Todo: (4.20)부분이 나중에 밑처럼 바뀐다.
        if (!(o instanceof Article that)) return false;
        return this.getId() != null && this.getId().equals(that.getId());
    }
    @Override
    public int hashCode() {
        //자동 생성되는 부분
        //return Objects.hash(id); //이 동일성 검사에서 hashCode는 이 id만 가지고 hashing한다.
        //Todo: 위부분이 나중에 밑처럼 바뀐다.
        return Objects.hash(this.getId());
    }

}
