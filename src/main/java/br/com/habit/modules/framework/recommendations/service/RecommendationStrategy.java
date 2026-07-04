package br.com.habit.modules.framework.recommendations.service;

import br.com.habit.modules.framework.user.model.User;

public abstract class RecommendationStrategy {
    protected int scoreByLocation(User me, User other) {
        if (me.getCity() != null && me.getCity().equalsIgnoreCase(other.getCity())) {
            return 5;
        } else if (me.getState() != null && me.getState().equalsIgnoreCase(other.getState())) {
            return 3;
        }
        return 0;
    }
    
    public abstract int calculateScore(User me, User other);
}
