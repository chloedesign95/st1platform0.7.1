package com.st1.platform.pi.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QParkingInfo is a Querydsl query type for ParkingInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QParkingInfo extends EntityPathBase<ParkingInfo> {

    private static final long serialVersionUID = 1024133217L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QParkingInfo parkingInfo = new QParkingInfo("parkingInfo");

    public final StringPath contractCompany = createString("contractCompany");

    public final DateTimePath<java.time.LocalDateTime> contractedAt = createDateTime("contractedAt", java.time.LocalDateTime.class);

    public final StringPath localCode = createString("localCode");

    public final NumberPath<Integer> orderCompany = createNumber("orderCompany", Integer.class);

    public final StringPath parkContent = createString("parkContent");

    public final StringPath parkingAddr = createString("parkingAddr");

    public final StringPath parkingName = createString("parkingName");

    public final NumberPath<Integer> parkingType = createNumber("parkingType", Integer.class);

    public final NumberPath<Long> parkNo = createNumber("parkNo", Long.class);

    public final DateTimePath<java.time.LocalDateTime> serviceDate = createDateTime("serviceDate", java.time.LocalDateTime.class);

    public final StringPath tagName = createString("tagName");

    public final com.st1.platform.all.domain.QUserInfo userInfo;

    public QParkingInfo(String variable) {
        this(ParkingInfo.class, forVariable(variable), INITS);
    }

    public QParkingInfo(Path<? extends ParkingInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QParkingInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QParkingInfo(PathMetadata metadata, PathInits inits) {
        this(ParkingInfo.class, metadata, inits);
    }

    public QParkingInfo(Class<? extends ParkingInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userInfo = inits.isInitialized("userInfo") ? new com.st1.platform.all.domain.QUserInfo(forProperty("userInfo")) : null;
    }

}

