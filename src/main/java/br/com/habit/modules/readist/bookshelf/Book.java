package br.com.habit.modules.readist.bookshelf;

import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.framework.user_collection.model.UserCollectionItem;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "books")
public class Book extends UserCollectionItem {
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReadingStatusType status;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookLengthType length;

    public Book(
            User user,
            ReadingStatusType status,
            String title,
            String author,
            BookLengthType length) {
        this.user = user;
        this.status = status;
        this.title = title;
        this.author = author;
        this.length = length;
    }
}