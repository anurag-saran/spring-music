 create table if not exists Album (
        trackCount integer not null,
        id varchar(40) not null,
        albumId varchar(255),
        artist varchar(255),
        genre varchar(255),
        releaseYear varchar(255),
        title varchar(255),
        primary key (id)
    );