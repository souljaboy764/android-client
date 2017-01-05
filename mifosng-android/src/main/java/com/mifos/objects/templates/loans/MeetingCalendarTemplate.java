package com.mifos.objects.templates.loans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by souljaboy764 on 5/1/17.
 */

public class MeetingCalendarTemplate implements Parcelable{

    Integer duration;

    CalendarOptionsType type;

    Boolean repeating;

    Frequency frequency;

    Integer interval;

    RepeatsOnNthDayOfMonth repeatsOnNthDayOfMonth;

    RepeatsOnDay repeatsOnDay;

    Integer firstReminder;

    Integer secondReminder;

    RemindBy remindBy;

    List<EntityType> entityTypeOptions;

    List<CalendarType> calendarTypeOptions;

    List<RemindBy> remindByOptions;

    List<Frequency> frequencyOptions;

    List<RepeatsOnDay> repeatsOnDayOptions;

    List<FrequencyNthDayType> frequencyNthDayTypeOptions;

    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public CalendarOptionsType getType() {
        return type;
    }
    public void setType(CalendarOptionsType type) {
        this.type = type;
    }

    public Boolean getRepeating() {
        return repeating;
    }
    public void setRepeating(Boolean repeating) {
        this.repeating = repeating;
    }

    public Frequency getFrequency() {
        return frequency;
    }
    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public Integer getInterval() {
        return interval;
    }
    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public RepeatsOnNthDayOfMonth getRepeatsOnNthDayOfMonth() {
        return repeatsOnNthDayOfMonth;
    }
    public void setRepeatsOnNthDayOfMonth(RepeatsOnNthDayOfMonth repeatsOnNthDayOfMonth) {
        this.repeatsOnNthDayOfMonth = repeatsOnNthDayOfMonth;
    }

    public RepeatsOnDay getRepeatsOnDay() {
        return repeatsOnDay;
    }
    public void setRepeatsOnDay(RepeatsOnDay repeatsOnDay) {
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

    public RemindBy getRemindBy() {
        return remindBy;
    }
    public void setRemindBy(RemindBy remindBy) {
        this.remindBy = remindBy;
    }

    public List<EntityType> getEntityTypeOptions() {
        return entityTypeOptions;
    }
    public void setEntityTypeOptions(List<EntityType> entityTypeOptions) {
        this.entityTypeOptions = entityTypeOptions;
    }

    public List<CalendarType> getCalendarTypeOptions() {
        return calendarTypeOptions;
    }
    public void setCalendarTypeOptions(List<CalendarType> calendarTypeOptions) {
        this.calendarTypeOptions = calendarTypeOptions;
    }

    public List<RemindBy> getRemindByOptions() {
        return remindByOptions;
    }
    public void setRemindByOptions(List<RemindBy> remindByOptions) {
        this.remindByOptions = remindByOptions;
    }

    public List<Frequency> getFrequencyOptions() {
        return frequencyOptions;
    }
    public void setFrequencyOptions(List<Frequency> frequencyOptions) {
        this.frequencyOptions = frequencyOptions;
    }

    public List<RepeatsOnDay> getRepeatsOnDayOptions() {
        return repeatsOnDayOptions;
    }
    public void setRepeatsOnDayOptions(List<RepeatsOnDay> repeatsOnDayOptions) {
        this.repeatsOnDayOptions = repeatsOnDayOptions;
    }

    public List<FrequencyNthDayType> getFrequencyNthDayTypeOptions() {
        return frequencyNthDayTypeOptions;
    }
    public void setFrequencyNthDayTypeOptions(List<FrequencyNthDayType> frequencyNthDayTypeOptions) {
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
        this.repeatsOnDay = in.readParcelable(RepeatsOnDay.class.getClassLoader());
        this.firstReminder = in.readInt();
        this.secondReminder = in.readInt();
        this.remindBy = in.readParcelable(RemindBy.class.getClassLoader());
        this.entityTypeOptions = in.createTypedArrayList(EntityType.CREATOR);
        this.calendarTypeOptions = in.createTypedArrayList(CalendarType.CREATOR);
        this.remindByOptions = in.createTypedArrayList(RemindBy.CREATOR);
        this.frequencyOptions = in.createTypedArrayList(Frequency.CREATOR);
        this.repeatsOnDayOptions = in.createTypedArrayList(RepeatsOnDay.CREATOR);
        this.frequencyNthDayTypeOptions = in.createTypedArrayList(FrequencyNthDayType.CREATOR);
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
