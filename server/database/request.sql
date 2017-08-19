
/* Get all participant of an expense*/
select *
from t_participant
    INNER JOIN t_contribution ON (t_participant.participant_id = t_contribution.contribution_participant_id)
where   t_participant.participant_event_id = 6
    and t_contribution.contribution_expense_id = 3;


select * from t_expense where expense_event_id=1;

select *
from t_expense
    LEFT JOIN t_contribution ON (t_expense.expense_id = t_contribution.contribution_expense_id)
where   expense_event_id=1
    and expense_id=1;
