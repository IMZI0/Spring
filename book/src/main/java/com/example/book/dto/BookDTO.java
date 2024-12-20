package com.example.book.dto;

import com.example.book.domain.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class BookDTO {
     //생성 post
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post{
        @NotBlank(message = "제목은 필수 입력 값입니다.")
        @NotNull(message = "제목은 반드시 입력하셔야 합니다.")
        @Size(min = 1,max = 45, message = "제목은 45자 이하여야 합니다.")
        private String title;

        @NotBlank
        @NotNull
        @Size(max = 45)
        private String subtitle;

         @Size(max = 45)
        private String author;

         @Size(max = 45)
        private String publisher;
    }
    //전체수정 put
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Put{
        @NotBlank(message = "제목은 필수 입력 값입니다.")
        @NotNull(message = "제목은 반드시 입력하셔야 합니다.")
        @Size(min = 1,max = 45, message = "제목은 45자 이하여야 합니다.")
        private String title;

        @NotBlank
        @NotNull
        @Size(max = 45)
        private String subtitle;

        @Size(max = 45)
        private String author;

        @Size(max = 45)
        private String publisher;

        private Book.Status status;
    }

    //일부 수정 patch
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch{
        private Book.Status status;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Rollback{

        @NotBlank(message = "제목은 필수 입력 값입니다.")
        @NotNull(message = "제목은 반드시 입력하셔야 합니다.")
        @Size(min = 1,max = 45, message = "제목은 45자 이하여야 합니다.")
        private String title;

        @NotBlank
        @NotNull
        @Size(max = 45)
        private String subtitle;

        @Size(max = 45)
        private String publisher;
    }

    //조회 get
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private Long id;
        private String title;
        private String subtitle;
        private String author;
        private String publisher;
        private Book.Status status;
    }
}
