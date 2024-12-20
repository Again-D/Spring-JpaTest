package com.thehecklers.jpatest;

import com.thehecklers.jpatest.domain.Memo;
import com.thehecklers.jpatest.repository.MemoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootApplication
public class JpatestApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpatestApplication.class, args);
    }

}

@Component
class DataLoader {
    private final MemoRepository memoRepository;
    DataLoader(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @PostConstruct
    public void loadData() {
//        this.memoRepository.saveAll(List.of(
//                new Memo("Cafe Cereza"),
//                new Memo("Cafe Ganador"),
//                new Memo("Cafe Lareno"),
//                new Memo("Cafe Tres Pontas")
//                ));
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = new Memo("Sample..."+i);
            memoRepository.save(memo);
        });
    }

}


