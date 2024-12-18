package com.example.demo.repository;

import com.example.demo.entity.QReservation;
import com.example.demo.entity.Reservation;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReservationRepositoryQuery {

	private final JPAQueryFactory jpaQueryFactory;

	private final QReservation reservation = QReservation.reservation;

	public List<Reservation> findReservationUserIdAndItemId(Long userId, Long itemId){

		return jpaQueryFactory.select(reservation)
			.from(reservation)
			.leftJoin(reservation.item).fetchJoin()
			.leftJoin(reservation.user).fetchJoin()
			.where(
				userIdEq(userId),
				itemIdEq(itemId)
			).fetch();
	}

	private BooleanExpression userIdEq(Long userId){
		return Objects.nonNull(userId) ? reservation.user.id.eq(userId) : null;
	}

	private BooleanExpression itemIdEq(Long itemId){
		return Objects.nonNull(itemId) ? reservation.item.id.eq(itemId) : null;
	}

}
