package com.b5wang.javalab.datastore.cassandra.otacampaign.entities;

import java.util.UUID;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * <p>A campaign always targets a set of cards. This set of cards is broken into several groups (batches).
 * Only one Kubernetes Pod may process one batch at the same time. Hence this entity record holds two
 * important information:
 * <ul>
 *  <li>The ID of the Pod processing the batch.</li>
 *  <li>The ID of the batch being processed: Campaign ID, pagingState</li>
 * </ul>
 * On top of those, we also need to keep track of the processing itself:
 * <ul>
 *  <li>When it was last updated.</li>
 *  <li>Whether it is still ongoing or already finished.</li>
 * </ul>
 * </p>
 * <p>The content and size of a batch are determined by the pagingState variable; this is a mechanism
 * provided by Cassandra to return a subset of records from the database.</p>
 *
 * @author inugraha
 */
public class TargetPodExecutionEntity {

    private UUID campaignId;

    private String executionStep = TargetState.CREATED.name();

    private UUID sequenceId;

    private String currentPagingState;

    private String nextPagingState;

    private UUID podId;

    private TargetExecutionState state;

    private long updateTime;

    public TargetPodExecutionEntity() {
    }

    public TargetPodExecutionEntity(UUID campaignId, String executionStep, UUID sequenceId, String currentPagingState, String nextPagingState, UUID podId,
            TargetExecutionState state, long updateTime) {
        this.campaignId = campaignId;
        this.currentPagingState = currentPagingState == null ? "NULL" : currentPagingState;
        this.sequenceId = sequenceId;
        this.nextPagingState = nextPagingState == null ? "NULL" : nextPagingState;
        this.podId = podId;

        if (state == null) {
            throw new IllegalArgumentException("TargetPodExecution State must not be empty.");
        }
        this.state = state;

        this.updateTime = updateTime;
        this.executionStep = executionStep;
    }

    public UUID getCampaignId() {
        return campaignId;
    }

    public TargetPodExecutionEntity setCampaignId(UUID campaignId) {
        this.campaignId = campaignId;
        return this;
    }

    public String getExecutionStep() {
        return executionStep;
    }

    public TargetPodExecutionEntity setExecutionStep(String executionStep) {
        this.executionStep = executionStep;
        return this;
    }

    public UUID getSequenceId() {
        return sequenceId;
    }

    public TargetPodExecutionEntity setSequenceId(UUID sequenceId) {
        this.sequenceId = sequenceId;
        return this;
    }

    public String getCurrentPagingState() {
        return currentPagingState;
    }

    public TargetPodExecutionEntity setCurrentPagingState(String currentPagingState) {
        this.currentPagingState = currentPagingState;
        return this;
    }

    public String getNextPagingState() {
        return nextPagingState;
    }

    public TargetPodExecutionEntity setNextPagingState(String nextPagingState) {
        this.nextPagingState = nextPagingState == null ? "NULL" : nextPagingState;
        return this;
    }

    public UUID getPodId() {
        return podId;
    }

    public TargetPodExecutionEntity setPodId(UUID podId) {
        this.podId = podId;
        return this;
    }

    public TargetExecutionState getState() {
        return state;
    }

    public TargetPodExecutionEntity setState(TargetExecutionState state) {
        if (state == null) {
            throw new IllegalArgumentException("TargetPodExecution State must not be empty.");
        }
        this.state = state;
        return this;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public TargetPodExecutionEntity setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TargetPodExecutionEntity that = (TargetPodExecutionEntity) o;
        return Objects.equal(campaignId, that.campaignId) && Objects.equal(podId, that.podId)
                && Objects.equal(currentPagingState, that.currentPagingState)
                && Objects.equal(nextPagingState, that.nextPagingState)
                && Objects.equal(state, that.state)
                && Objects.equal(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(campaignId, currentPagingState, nextPagingState, podId, state, updateTime);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("campaignId", campaignId).add("currentPagingState", currentPagingState)
                .add("nextPagingState", nextPagingState).add("podId", podId)
                .add("state", state).add("updateTime", updateTime).toString();
    }
}
