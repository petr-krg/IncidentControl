/* custom javascript here */
// функция загруки инфорации по профайлу пользователя текущего.
function loadUserProfile() {
    fetch('/user/profile-info')
        .then(response => response.json())
        .then(data => {
            document.getElementById('lastName').value = data.userLastName;
            document.getElementById('firstName').value = data.userFirstName;
            document.getElementById('patName').value = data.userPatName;
            document.getElementById('userName').value = data.userDisplayName;
            document.getElementById('userPhone').value = data.userPhone;
            document.getElementById('birthdate').value = data.userBirthDate;
            document.getElementById('userLogin').value = data.userLogin;
            document.getElementById('userEmail').value = data.userEmail;

            fillRoleSelect(data.allRoles, data.currentRole);
            fillDepartmentSelect(data.allDepartment, data.currentDepartment);
            fillPositionSelect(data.allPosition, data.currentPosition);
            fillDivisionSelect(data.allDivision, data.currentDivision);

        })
        .catch(error => console.error('Error:', error));
}

// =======================================================================================
function updateSelectOptions(selectId, options) {
    const select = document.getElementById(selectId);
    select.innerHTML = '';

    options.forEach(option => {
        let optionElement = document.createElement('option');
        optionElement.value = option;
        optionElement.text = option;
        select.appendChild(optionElement);
    });
}

function updateDivision(selectedIndex) {
    var selectedElement = document.getElementById('userDepartment');
    var departmentName = selectedElement.options[selectedIndex].text;
    var encodedDepartmentName = encodeURIComponent(departmentName);

    fetch(`/organizational-structure/get-division/${encodedDepartmentName}`)
        .then(response => response.json())
        .then(data => updateSelectOptions('userDivision', data))
        .catch(error => console.error('Error:', error));
}

function updatePosition(selectedIndex) {
    var selectedElement = document.getElementById('userDivision');
    var divisionName = selectedElement.options[selectedIndex].text;
    var encodedDivisionName = encodeURIComponent(divisionName);

    fetch(`/organizational-structure/get-positions/${encodedDivisionName}`)
        .then(response => response.json())
        .then(data => updateSelectOptions('userPosition', data))
        .catch(error => console.error('Error:', error));
}
//========================================================================================
function fillRoleSelect(allRoles, currentRole) {
    const select = document.getElementById('userRole');
    select.innerHTML = '';

    allRoles.forEach(role => {
        let option = document.createElement('option');
        option.value = role;
        option.text = role;
        option.selected = role === currentRole;
        select.appendChild(option);
    });
}

function fillDepartmentSelect(allDepartment, currentDepartment) {
    const select = document.getElementById('userDepartment');
    select.innerHTML = '';

    allDepartment.forEach(department => {
        let option = document.createElement('option');
        option.value = department;
        option.text = department;
        option.selected = department === currentDepartment;
        select.appendChild(option);
    });
}

function fillPositionSelect(allPosition, currentPosition) {
        const select = document.getElementById('userPosition');
        select.innerHTML = '';

        allPosition.forEach(position => {
            let option = document.createElement('option');
            option.value = position;
            option.text = position;
            option.selected = position === currentPosition;
            select.appendChild(option);
        });
}

function fillDivisionSelect(allDivision, currentDivision) {
        const select = document.getElementById('userDivision');
        select.innerHTML = '';

        allDivision.forEach(division => {
            let option = document.createElement('option');
            option.value = division;
            option.text = division;
            option.selected = division === currentDivision;
            select.appendChild(option);
        });
}

function onDepartmentChange() {
    const departmentId = document.getElementById('departmentSelect').value;
    fetch(`/get-departments/${departmentId}`)
        .then(response => response.json())
        .then(departments => {
            const departmentSelect = document.getElementById('departmentSelect');
            departmentSelect.innerHTML = '';

            departments.forEach(department => {
                const option = document.createElement('option');
                option.value = department.id;
                option.text = department.name;
                departmentSelect.appendChild(option);
            });

            onDepartmentOrDivisionChange();
        })
        .catch(error => console.error('Error:', error));
}

function onDepartmentOrDivisionChange() {
    const officeId = document.getElementById('officeSelect').value;
    fetch(`/get-positions/${officeId}`)
        .then(response => response.json())
        .then(positions => {
        })
        .catch(error => console.error('Error:', error));
}