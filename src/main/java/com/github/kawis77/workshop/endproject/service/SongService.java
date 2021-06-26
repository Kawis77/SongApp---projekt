package com.github.kawis77.workshop.endproject.service;

import com.github.kawis77.workshop.endproject.model.Song;
import com.github.kawis77.workshop.endproject.repository.SongRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService implements SongRepository {


    @Override
    public List<Song> findAll() {
        return null;
    }

    @Override
    public List<Song> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Song> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Song> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Song song) {

    }

    @Override
    public void deleteAll(Iterable<? extends Song> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Song> S save(S s) {
        return null;
    }

    @Override
    public <S extends Song> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Song> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Song> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Song> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Song getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Song> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Song> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Song> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Song> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Song> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Song> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public Song findBySongName(String name) {
        return null;
    }

    @Override
    public Song removeBySongName(Song name) {
        return null;
    }
}
