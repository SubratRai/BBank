CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_users_email (email)
);

CREATE TABLE IF NOT EXISTS accounts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(20) NOT NULL UNIQUE,
    balance DECIMAL(19,2) NOT NULL,
    account_type VARCHAR(20) NOT NULL,
    frozen BIT(1) NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_accounts_user FOREIGN KEY (user_id) REFERENCES users(id),
    INDEX idx_accounts_user_id (user_id),
    INDEX idx_accounts_account_number (account_number)
);

CREATE TABLE IF NOT EXISTS beneficiaries (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    account_number VARCHAR(20) NOT NULL,
    bank_name VARCHAR(100) NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_beneficiary_user FOREIGN KEY (user_id) REFERENCES users(id),
    INDEX idx_beneficiaries_user_id (user_id),
    INDEX idx_beneficiaries_account_number (account_number)
);

CREATE TABLE IF NOT EXISTS transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(20) NOT NULL,
    amount DECIMAL(19,2) NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    source_account_id BIGINT NULL,
    destination_account_id BIGINT NULL,
    CONSTRAINT fk_source_account FOREIGN KEY (source_account_id) REFERENCES accounts(id),
    CONSTRAINT fk_destination_account FOREIGN KEY (destination_account_id) REFERENCES accounts(id),
    INDEX idx_transactions_timestamp (timestamp),
    INDEX idx_transactions_source (source_account_id),
    INDEX idx_transactions_destination (destination_account_id)
);
