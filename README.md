# Istasyon Project

## Overview

**Istasyon** is a job-searching platform developed using Java Spring Boot, specifically tailored for blue-collar workers. It provides an efficient and user-friendly environment for job seekers to create profiles, search for job opportunities, and connect with employers. Companies can use the platform to filter potential candidates, post job offers, and receive candidate suggestions through a machine learning (ML) algorithm.

## Key Features

- **Profile Creation:** Simple and intuitive interface for users to create and manage their profiles.
- **Advanced Filtering:** Companies can utilize advanced filtering options to find the most suitable candidates based on various criteria.
- **Job Offers:** A streamlined process for companies to post job offers and for users to apply for jobs.
- **ML Algorithm for Suggestions:** The platform includes a machine learning algorithm that provides company suggestions based on user profiles and activity.

## OpenAPI Documentation

The project includes an OpenAPI specification for the backend API. Below is a summary of the available API endpoints:

### API Info

- **OpenAPI Version:** 3.1.0
- **Title:** istasyon_backend API
- **Description:** API for Istasyon backend services
- **Version:** 1.0.0

### Available Endpoints

- **GET /my-authority:** Retrieves the authority level of the current user.
- **POST /login:** Handles user login and returns authentication tokens.
- **GET /profile/:** Fetches the profile details of a user.
- **GET /dashboard:** Retrieves the dashboard data.
- **GET /job/list:** Returns a list of available jobs based on the specified query parameters.
- **GET /job/view:** Retrieves detailed information about a specific job.
- **POST /company/register:** Registers a new company on the platform.
- **POST /user/register:** Registers a new user on the platform.
- **POST /company/jobAdd/addSkill:** Adds a required skill to a job posting.
- **POST /company/jobAdd/create:** Creates a new job posting.
- **POST /company/jobAdd/deleteSkill:** Removes a skill from a job posting.
- **GET /company/jobAdd/view:** Retrieves detailed information about a job posting.
- **POST /company/profile/edit:** Allows a company to edit its profile information.
- **GET /applications/applicant/get:** Retrieves information about a specific job applicant.
- **GET /applications/get:** Retrieves a list of applications for a specific job ad.
- **POST /comment/add:** Adds a comment or review for a job or employer.
- **GET /comment/get:** Retrieves comments or reviews based on the specified query parameters.
- **POST /user/skill/add:** Allows a user to add new skills to their profile.
- **POST /user/language/add:** Adds a new language to the user's profile.
- **GET /user/profile/:** Retrieves the user's profile details.
- **POST /user/profile/edit:** Allows a user to edit their profile information.
- **POST /user/employment/add:** Adds new employment details to the user's profile.
- **POST /user/employment/previous/add:** Adds previous employment details to the user's profile.
- **DELETE /user/employment/previous/delete/{id}:** Deletes a specific employment record.
- **POST /user/employment/retire:** Marks the user as retired and updates employment status.
- **POST /user/job/apply:** Submits a job application for a specific job post.
- **GET /user/job/isQualified:** Checks if the user is qualified for a specific job.

## Components

The API uses various components and schemas to structure the data. Some key components include:

- **UserDTO:** Schema for user-related data.
- **CompanyRegisterDTO:** Schema for company registration data.
- **RegisterDTO:** Schema for user registration data.
- **JobSkillDTO:** Schema for job skill data.
- **JobAddDTO:** Schema for job ad data.
- **CommentDTO:** Schema for comments or reviews.
- **EmployeeSkillDTO:** Schema for employee skills.
- **EmployeeJobDTO:** Schema for employee job history.
- **ApplicationDTO:** Schema for job applications.
