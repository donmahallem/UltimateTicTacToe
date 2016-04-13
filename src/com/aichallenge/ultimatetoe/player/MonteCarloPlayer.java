package com.aichallenge.ultimatetoe.player;

import com.aichallenge.ultimatetoe.tensor.MonteCarloTensor;

/**
 * Tensor player only utilizing the MonteCarloTensor
 *
 * @see MonteCarloTensor
 */
public final class MonteCarloPlayer extends TensorPlayer {

    public MonteCarloPlayer(int pPlayerId) {
        super(pPlayerId, new MonteCarloTensor(pPlayerId));
    }

}
