package httpapi.authz

# bob is alice's manager, and betty is charlie's.
subordinates := {"alice": [],"alok": [], "charlie": ["bob"], "bob": ["alice","alok"], "betty": ["charlie"]}

default allow := false

# Allow users to get their own salaries.
allow {
    input.method == "GET"
    input.path == ["finance", "salary", input.user]
}

# Allow managers to get their subordinates' salaries.
allow {
    some username
    input.method == "GET"
    input.path = ["finance", "salary", username]
    subordinates[input.user][_] == username
}

# Allow managers to set their subordinates' salaries.
allow {
    some username
    input.method == "PUT"
    input.path = ["finance", "salary", username]
    subordinates[input.user][_] == username
}

# Allow managers to get their subordinates' performance reviews.
allow {
    some username
    input.method == "GET"
    input.path = ["hr", "performance_review", username]
    subordinates[input.user][_] == username
}

# Allow managers to set their subordinates' performance reviews.
allow {
    some username
    input.method == "PUT"
    input.path = ["hr", "performance_review", username]
    subordinates[input.user][_] == username
}

# Allow managers to get their subordinates' personal information.
allow {
    some username
    input.method == "GET"
    input.path = ["hr", "personal_information", username]
    subordinates[input.user][_] == username
}

# Allow managers to set their subordinates' personal information.
allow {
    some username
    input.method == "PUT"
    input.path = ["hr", "personal_information", username]
    subordinates[input.user][_] == username
}

# Allow managers to get their subordinates' work authorizations.
allow {
    some username
    input.method == "GET"
    input.path = ["hr", "work_authorization", username]
    subordinates[input.user][_] == username
}

# Allow managers to set their subordinates' work authorizations.
allow {
    some username
    input.method == "PUT"
    input.path = ["hr", "work_authorization", username]
    subordinates[input.user][_] == username
}

# Allow managers to get their subordinates' benefits.
allow {
    some username
    input.method == "GET"
    input.path = ["hr", "benefits", username]
    subordinates[input.user][_] == username
}

# Allow managers to set their subordinates' benefits.
allow {
    some username
    input.method == "PUT"
    input.path = ["hr", "benefits", username]
    subordinates[input.user][_] == username
}

# Allow managers to get their subordinates' time off.
allow {
    some username
    input.method == "GET"
    input.path = ["hr", "time_off", username]
    subordinates[input.user][_] == username
}

# Allow managers to set their subordinates' time off.
allow {
    some username
    input.method == "PUT"
    input.path = ["hr", "time_off", username]
    subordinates[input.user][_] == username
}

