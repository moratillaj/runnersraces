create table races (
    race_name varchar(250) not null,
    runner_email varchar(255) not null,
    race_date date not null,
    distance_km float not null,
    completed tinyint not null,
    completed_race_time_seconds int not null,
    primary key (race_name, runner_email, race_date));
