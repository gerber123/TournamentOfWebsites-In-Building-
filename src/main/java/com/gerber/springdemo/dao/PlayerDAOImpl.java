package com.gerber.springdemo.dao;

import com.gerber.springdemo.entity.PlayerForTheExp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
public class PlayerDAOImpl implements PlayerDAO
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<PlayerForTheExp> getAllPlayers() {
        Session session =sessionFactory.getCurrentSession();
//        List<PlayerForTheExp> listOfPlayers = session.createQuery("from PlayerForTheExp",PlayerForTheExp.class).getResultList();
//        return listOfPlayers;
        // get the current hibernate session

        // create a query  ... sort by last name
        Query<PlayerForTheExp> theQuery = session.createQuery("from PlayerForTheExp order by lastName", PlayerForTheExp.class);

        // execute query and get result list
        List<PlayerForTheExp> playerForTheExps = theQuery.getResultList();

        // return the results
        return playerForTheExps;
    }
    @Override
    public void savePlayer(PlayerForTheExp playerForTheExp)
    {
        Session session =sessionFactory.getCurrentSession();
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        java.sql.Date date = new java.sql.Date(currentDate.getTime());

        playerForTheExp.setDate(date);
        session.saveOrUpdate(playerForTheExp);

    }
    @Override
    public PlayerForTheExp getPlayer(int theId) {
        Session session =sessionFactory.getCurrentSession();
        PlayerForTheExp player=session.get(PlayerForTheExp.class,theId);
        return player;
    }
    @Override
    public void deletePlayer(int theId) {
        Session session=sessionFactory.getCurrentSession();
        PlayerForTheExp player=session.get(PlayerForTheExp.class,theId);
        session.delete(player);
    }
    @Override
    public List<PlayerForTheExp> findPlayers(String nameOfPlayer) {
        Session session=sessionFactory.getCurrentSession();
        Query q=null;
        if(nameOfPlayer!=null&&nameOfPlayer.trim().length()>0)
        {
            q=session.createQuery ("from PlayerForTheExp where lower(firstName) like :theName or Lower(lastName) like :theName",PlayerForTheExp.class);
            q.setParameter("theName","%"+nameOfPlayer.toLowerCase()+"%");
        }
        else
        {
            q =session.createQuery("from PlayerForTheExp", PlayerForTheExp.class);
        }
        List<PlayerForTheExp> players =q.getResultList();
        return players;
    }

}
