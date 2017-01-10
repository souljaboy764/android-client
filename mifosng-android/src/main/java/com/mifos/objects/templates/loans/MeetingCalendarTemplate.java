package com.mifos.objects.templates.loans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;
import java.util.List;

import com.mifos.objects.collectionsheet.EntityType;

/**
 * Created by souljaboy764 on 5/1/17.
 */

public class MeetingCalendarTemplate implements Parcelable{

    Integer duration;

    EntityType type;

    Boolean repeating;

    EntityType frequency;

    Integer interval;

    EntityType repeatsOnNthDayOfMonth;

    EntityType repeatsOnDay;

    Integer firstReminder;

    Integer secondReminder;

    EntityType remindBy;

    List<EntityType> entityTypeOptions;

    List<EntityType> calendarTypeOptions;

    List<EntityType> remindByOptions;

    List<EntityType> frequencyOptions;

    List<EntityType> repeatsOnDayOptions;

    List<EntityType> frequencyNthDayTypeOptions;

    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public EntityType getType() {
        return type;
    }
    public void setType(EntityType type) {
        this.type = type;
    }

    public Boolean getRepeating() {
        return repeating;
    }
    public void setRepeating(Boolean repeating) {
        this.repeating = repeating;
    }

    public EntityType getFrequency() {
        return frequency;
    }
    public void setFrequency(EntityType frequency) {
        this.frequency = frequency;
    }

    public Integer getInterval() {
        return interval;
    }
    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public EntityType getRepeatsOnNthDayOfMonth() {
        return repeatsOnNthDayOfMonth;
    }
    public void setRepeatsOnNthDayOfMonth(EntityType repeatsOnNthDayOfMonth) {
        this.repeatsOnNthDayOfMonth = repeatsOnNthDayOfMonth;
    }

    public EntityType getRepeatsOnDay() {
        return repeatsOnDay;
    }
    public void setRepeatsOnDay(EntityType repeatsOnDay) {
        this.repeatsOnDay = repeatsOnDay;
    }

    public Integer getFirstReminder() {
        return firstReminder;
    }
    public void setFirstReminder(Integer firstReminder) {
        this.firstReminder = firstReminder;
    }

    public Integer getSecondReminder() {
        return secondReminder;
    }
    public void setSecondReminder(Integer secondReminder) {
        this.secondReminder = secondReminder;
    }

    public EntityType getRemindBy() {
        return remindBy;
    }
    public void setRemindBy(EntityType remindBy) {
        this.remindBy = remindBy;
    }

    public List<EntityType> getEntityTypeOptions() {
        return entityTypeOptions;
    }
    public void setEntityTypeOptions(List<EntityType> entityTypeOptions) {
        this.entityTypeOptions = entityTypeOptions;
    }

    public List<EntityType> getCalendarTypeOptions() {
        return calendarTypeOptions;
    }
    public void setCalendarTypeOptions(List<EntityType> calendarTypeOptions) {
        this.calendarTypeOptions = calendarTypeOptions;
    }

    public List<EntityType> getRemindByOptions() {
        return remindByOptions;
    }
    public void setRemindByOptions(List<EntityType> remindByOptions) {
        this.remindByOptions = remindByOptions;
    }

    public List<com.mifos.objects.collectionsheet.EntityType> getFrequencyOptions() {
        return frequencyOptions;
    }
    public void setFrequencyOptions(List<EntityType> frequencyOptions) {
        this.frequencyOptions = frequencyOptions;
    }

    public List<EntityType> getRepeatsOnDayOptions() {
        return repeatsOnDayOptions;
    }
    public void setRepeatsOnDayOptions(List<EntityType> repeatsOnDayOptions) {
        this.repeatsOnDayOptions = repeatsOnDayOptions;
    }

    public List<EntityType> getFrequencyNthDayTypeOptions() {
        return frequencyNthDayTypeOptions;
    }
    public void setFrequencyNthDayTypeOptions(List<EntityType> frequencyNthDayTypeOptions) {
        this.frequencyNthDayTypeOptions = frequencyNthDayTypeOptions;
    }

    @Override
    public String toString() {
        return "MeetingCalendarTemplate{" +
                "duration=" + duration +
                ", type=" + type +
                ", repeating=" + repeating +
                ", frequency=" + frequency +
                ", interval=" + interval +
                ", repeatsOnNthDayOfMonth=" + repeatsOnNthDayOfMonth +
                ", repeatsOnDay=" + repeatsOnDay +
                ", firstReminder=" + firstReminder +
                ", secondReminder=" + secondReminder +
                ", remindBy=" + remindBy +
                ", entityTypeOptions=" + Arrays.toString(entityTypeOptions.toArray()) +
                ", calendarTypeOptions=" + Arrays.toString(calendarTypeOptions.toArray()) +
                ", remindByOptions=" + Arrays.toString(remindByOptions.toArray()) +
                ", frequencyOptions=" + Arrays.toString(frequencyOptions.toArray()) +
                ", repeatsOnDayOptions=" + Arrays.toString(repeatsOnDayOptions.toArray()) +
                ", frequencyNthDayTypeOptions=" + Arrays.toString(frequencyNthDayTypeOptions.toArray()) +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.duration);
        dest.writeParcelable(this.type, flags);
        dest.writeValue(this.repeating);
        dest.writeParcelable(this.frequency, flags);
        dest.writeInt(this.interval);
        dest.writeParcelable(this.repeatsOnNthDayOfMonth, flags);
        dest.writeParcelable(this.repeatsOnDay, flags);
        dest.writeInt(this.firstReminder);
        dest.writeInt(this.secondReminder);
        dest.writeParcelable(this.remindBy, flags);
        dest.writeTypedList(this.entityTypeOptions);
        dest.writeTypedList(this.calendarTypeOptions);
        dest.writeTypedList(this.remindByOptions);
        dest.writeTypedList(this.frequencyOptions);
        dest.writeTypedList(this.repeatsOnDayOptions);
        dest.writeTypedList(this.frequencyNthDayTypeOptions);
    }

    public MeetingCalendarTemplate() {
    }

    protected MeetingCalendarTemplate(Parcel in) {
        this.duration = in.readInt();
        this.type = in.readParcelable(Type.class.getClassLoader());
        this.repeating = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.frequency = in.readParcelable(Frequency.class.getClassLoader());
        this.interval = in.readInt();
        this.repeatsOnNthDayOfMonth = in.readParcelable(RepeatsOnNthDayOfMonth.class.getClassLoader());
        this.repeatsOnDay = in.readParcelable(EntityType.class.getClassLoader());
        this.firstReminder = in.readInt();
        this.secondReminder = in.readInt();
        this.remindBy = in.readParcelable(EntityType.class.getClassLoader());
        this.entityTypeOptions = in.createTypedArrayList(EntityType.CREATOR);
        this.calendarTypeOptions = in.createTypedArrayList(EntityType.CREATOR);
        this.remindByOptions = in.createTypedArrayList(EntityType.CREATOR);
        this.frequencyOptions = in.createTypedArrayList(EntityType.CREATOR);
        this.repeatsOnDayOptions = in.createTypedArrayList(EntityType.CREATOR);
        this.frequencyNthDayTypeOptions = in.createTypedArrayList(EntityType.CREATOR);
    }

    public static final Parcelable.Creator<MeetingCalendarTemplate> CREATOR = new Parcelable
            .Creator<MeetingCalendarTemplate>() {
        @Override
        public MeetingCalendarTemplate createFromParcel(Parcel source) {
            return new MeetingCalendarTemplate(source);
        }

        @Override
        public MeetingCalendarTemplate[] newArray(int size) {
            return new MeetingCalendarTemplate[size];
        }
    };


}
