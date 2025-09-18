# Data Structure Benchmark Project

## Project Overview
A comprehensive benchmarking application that compares the performance of different data structures (arrays, linked lists, stacks, queues) across various operations. The project demonstrates performance analysis, memory usage monitoring, and statistical analysis in C++.

## Project Specifications

### Core Features
- Performance benchmarking for multiple data structures
- Memory usage analysis and monitoring
- Statistical analysis with mean, median, standard deviation
- Graph generation for performance visualization
- Configurable test parameters and scenarios
- CSV export for external analysis
- Real-time performance monitoring
- Cross-platform performance comparison

### Technical Requirements
- **Language**: C++17 or higher
- **Dependencies**: Standard C++ libraries, optional: Gnuplot for graphing
- **Platforms**: Linux, Windows, macOS
- **Build Tool**: CMake or Makefile
- **Memory Profiling**: Custom memory tracking

## Project Structure
```
data-structure-benchmark/
├── src/
│   ├── Benchmark.h
│   ├── Benchmark.cpp
│   ├── DataStructure.h
│   ├── ArrayBenchmark.h
│   ├── ArrayBenchmark.cpp
│   ├── LinkedListBenchmark.h
│   ├── LinkedListBenchmark.cpp
│   ├── StackBenchmark.h
│   ├── StackBenchmark.cpp
│   ├── QueueBenchmark.h
│   ├── QueueBenchmark.cpp
│   ├── Statistics.h
│   ├── Statistics.cpp
│   ├── MemoryProfiler.h
│   ├── MemoryProfiler.cpp
│   ├── ReportGenerator.h
│   ├── ReportGenerator.cpp
│   └── main.cpp
├── tests/
│   ├── test_benchmark.cpp
│   └── test_statistics.cpp
├── CMakeLists.txt
├── Makefile
└── README.md
```

## Detailed Specifications

### 1. Base Data Structure Interface

#### DataStructure.h
```cpp
#ifndef DATA_STRUCTURE_H
#define DATA_STRUCTURE_H

#include <chrono>
#include <vector>
#include <string>
#include <memory>

struct BenchmarkResult {
    std::string operation;
    std::string dataStructure;
    size_t dataSize;
    double averageTimeMs;
    double minTimeMs;
    double maxTimeMs;
    double standardDeviation;
    size_t memoryUsage;
    size_t iterations;
    
    BenchmarkResult() 
        : averageTimeMs(0), minTimeMs(0), maxTimeMs(0), 
          standardDeviation(0), memoryUsage(0), iterations(0) {}
};

class DataStructure {
public:
    virtual ~DataStructure() = default;
    
    // Virtual methods to be implemented by derived classes
    virtual void insert(int value) = 0;
    virtual void insertAt(int index, int value) = 0;
    virtual void remove(int value) = 0;
    virtual void removeAt(int index) = 0;
    virtual int search(int value) = 0;
    virtual int get(int index) = 0;
    virtual void clear() = 0;
    virtual size_t size() const = 0;
    virtual bool isEmpty() const = 0;
    
    // Benchmark-specific methods
    virtual std::string getName() const = 0;
    virtual void resize(size_t newSize) = 0;
    virtual void fillRandom(size_t count, int minVal = 0, int maxVal = 1000) = 0;
    
    // Memory usage tracking
    virtual size_t getMemoryUsage() const = 0;
    virtual void resetMemoryTracking() = 0;
};

#endif // DATA_STRUCTURE_H
```

### 2. Statistics and Analysis

#### Statistics.h
```cpp
#ifndef STATISTICS_H
#define STATISTICS_H

#include <vector>
#include <string>
#include <chrono>

class Statistics {
private:
    std::vector<double> data;
    bool isSorted;
    
    void sortData();
    
public:
    Statistics();
    explicit Statistics(const std::vector<double>& values);
    
    void addValue(double value);
    void addValues(const std::vector<double>& values);
    void clear();
    
    // Basic statistics
    double mean() const;
    double median() const;
    double mode() const;
    double standardDeviation() const;
    double variance() const;
    double min() const;
    double max() const;
    double range() const;
    
    // Percentiles
    double percentile(double p) const;
    double quartile(int q) const;
    double interquartileRange() const;
    
    // Distribution analysis
    bool isNormalDistribution(double threshold = 0.05) const;
    double skewness() const;
    double kurtosis() const;
    
    // Confidence intervals
    struct ConfidenceInterval {
        double lower;
        double upper;
        double confidence;
    };
    
    ConfidenceInterval confidenceInterval(double confidence = 0.95) const;
    
    // Outlier detection
    std::vector<double> detectOutliers() const;
    std::vector<size_t> detectOutlierIndices() const;
    
    // Summary
    struct Summary {
        double mean;
        double median;
        double stdDev;
        double min;
        double max;
        double q1;
        double q3;
        size_t count;
        std::vector<double> outliers;
    };
    
    Summary getSummary() const;
    
    // Export
    std::string toString(int precision = 6) const;
    std::vector<double> getData() const { return data; }
    size_t getCount() const { return data.size(); }
};

#endif // STATISTICS_H
```

#### Statistics.cpp (Key Methods)
```cpp
#include "Statistics.h"
#include <algorithm>
#include <numeric>
#include <cmath>
#include <iomanip>
#include <sstream>

double Statistics::mean() const {
    if (data.empty()) return 0.0;
    return std::accumulate(data.begin(), data.end(), 0.0) / data.size();
}

double Statistics::median() const {
    if (data.empty()) return 0.0;
    
    std::vector<double> sortedData = data;
    std::sort(sortedData.begin(), sortedData.end());
    
    size_t n = sortedData.size();
    if (n % 2 == 0) {
        return (sortedData[n/2 - 1] + sortedData[n/2]) / 2.0;
    } else {
        return sortedData[n/2];
    }
}

double Statistics::standardDeviation() const {
    if (data.size() < 2) return 0.0;
    
    double meanVal = mean();
    double sumSquaredDiffs = 0.0;
    
    for (double value : data) {
        double diff = value - meanVal;
        sumSquaredDiffs += diff * diff;
    }
    
    return std::sqrt(sumSquaredDiffs / (data.size() - 1));
}

double Statistics::percentile(double p) const {
    if (data.empty() || p < 0.0 || p > 1.0) return 0.0;
    
    std::vector<double> sortedData = data;
    std::sort(sortedData.begin(), sortedData.end());
    
    size_t n = sortedData.size();
    double index = p * (n - 1);
    size_t lowerIndex = static_cast<size_t>(std::floor(index));
    size_t upperIndex = static_cast<size_t>(std::ceil(index));
    
    if (lowerIndex == upperIndex) {
        return sortedData[lowerIndex];
    }
    
    double weight = index - lowerIndex;
    return sortedData[lowerIndex] * (1.0 - weight) + sortedData[upperIndex] * weight;
}

Statistics::Summary Statistics::getSummary() const {
    Summary summary;
    summary.mean = mean();
    summary.median = median();
    summary.stdDev = standardDeviation();
    summary.min = min();
    summary.max = max();
    summary.q1 = quartile(1);
    summary.q3 = quartile(3);
    summary.count = data.size();
    summary.outliers = detectOutliers();
    
    return summary;
}

std::string Statistics::toString(int precision) const {
    std::ostringstream oss;
    oss << std::fixed << std::setprecision(precision);
    
    auto summary = getSummary();
    
    oss << "Statistics Summary:\n";
    oss << "  Count: " << summary.count << "\n";
    oss << "  Mean: " << summary.mean << "\n";
    oss << "  Median: " << summary.median << "\n";
    oss << "  Std Dev: " << summary.stdDev << "\n";
    oss << "  Min: " << summary.min << "\n";
    oss << "  Max: " << summary.max << "\n";
    oss << "  Q1: " << summary.q1 << "\n";
    oss << "  Q3: " << summary.q3 << "\n";
    
    if (!summary.outliers.empty()) {
        oss << "  Outliers: ";
        for (size_t i = 0; i < summary.outliers.size(); ++i) {
            oss << summary.outliers[i];
            if (i < summary.outliers.size() - 1) oss << ", ";
        }
        oss << "\n";
    }
    
    return oss.str();
}
```

### 3. Memory Profiler

#### MemoryProfiler.h
```cpp
#ifndef MEMORY_PROFILER_H
#define MEMORY_PROFILER_H

#include <unordered_map>
#include <string>
#include <chrono>
#include <mutex>

class MemoryProfiler {
private:
    static std::unordered_map<std::string, size_t> allocations;
    static std::unordered_map<std::string, size_t> peakMemory;
    static std::mutex memoryMutex;
    
    static size_t totalAllocated;
    static size_t totalFreed;
    static size_t currentUsage;
    static size_t peakUsage;
    
public:
    // Memory tracking
    static void recordAllocation(const std::string& source, size_t size);
    static void recordDeallocation(const std::string& source, size_t size);
    
    // Statistics
    static size_t getCurrentUsage();
    static size_t getPeakUsage();
    static size_t getTotalAllocated();
    static size_t getTotalFreed();
    
    // Per-source statistics
    static size_t getUsageForSource(const std::string& source);
    static size_t getPeakForSource(const std::string& source);
    
    // Memory leak detection
    static bool hasMemoryLeaks();
    static std::vector<std::string> getMemoryLeakSources();
    
    // Reset and cleanup
    static void reset();
    static void clear();
    
    // Reporting
    static std::string generateReport();
    static void printReport();
};

// RAII Memory tracker
class MemoryTracker {
private:
    std::string source;
    size_t startUsage;
    
public:
    explicit MemoryTracker(const std::string& src);
    ~MemoryTracker();
    
    size_t getMemoryDelta() const;
};

#endif // MEMORY_PROFILER_H
```

### 4. Array Benchmark Implementation

#### ArrayBenchmark.h
```cpp
#ifndef ARRAY_BENCHMARK_H
#define ARRAY_BENCHMARK_H

#include "DataStructure.h"
#include <vector>
#include <algorithm>

class ArrayBenchmark : public DataStructure {
private:
    std::vector<int> data;
    size_t memoryUsage;
    
public:
    ArrayBenchmark();
    explicit ArrayBenchmark(size_t initialSize);
    ~ArrayBenchmark() override = default;
    
    // DataStructure interface
    void insert(int value) override;
    void insertAt(int index, int value) override;
    void remove(int value) override;
    void removeAt(int index) override;
    int search(int value) override;
    int get(int index) override;
    void clear() override;
    size_t size() const override;
    bool isEmpty() const override;
    
    // Benchmark interface
    std::string getName() const override;
    void resize(size_t newSize) override;
    void fillRandom(size_t count, int minVal = 0, int maxVal = 1000) override;
    size_t getMemoryUsage() const override;
    void resetMemoryTracking() override;
    
    // Array-specific operations
    void sort();
    void reverse();
    int binarySearch(int value);
    void removeDuplicates();
};

#endif // ARRAY_BENCHMARK_H
```

#### ArrayBenchmark.cpp (Key Methods)
```cpp
#include "ArrayBenchmark.h"
#include "MemoryProfiler.h"
#include <random>
#include <chrono>

ArrayBenchmark::ArrayBenchmark() : memoryUsage(0) {
    resetMemoryTracking();
}

ArrayBenchmark::ArrayBenchmark(size_t initialSize) : memoryUsage(0) {
    data.resize(initialSize, 0);
    resetMemoryTracking();
}

void ArrayBenchmark::insert(int value) {
    MemoryTracker tracker("ArrayBenchmark::insert");
    data.push_back(value);
    updateMemoryUsage();
}

void ArrayBenchmark::insertAt(int index, int value) {
    MemoryTracker tracker("ArrayBenchmark::insertAt");
    
    if (index > data.size()) {
        throw std::out_of_range("Index out of range");
    }
    
    data.insert(data.begin() + index, value);
    updateMemoryUsage();
}

void ArrayBenchmark::remove(int value) {
    MemoryTracker tracker("ArrayBenchmark::remove");
    
    auto it = std::find(data.begin(), data.end(), value);
    if (it != data.end()) {
        data.erase(it);
        updateMemoryUsage();
    }
}

void ArrayBenchmark::removeAt(int index) {
    MemoryTracker tracker("ArrayBenchmark::removeAt");
    
    if (index >= data.size()) {
        throw std::out_of_range("Index out of range");
    }
    
    data.erase(data.begin() + index);
    updateMemoryUsage();
}

int ArrayBenchmark::search(int value) {
    MemoryTracker tracker("ArrayBenchmark::search");
    
    auto it = std::find(data.begin(), data.end(), value);
    if (it != data.end()) {
        return static_cast<int>(std::distance(data.begin(), it));
    }
    return -1;
}

void ArrayBenchmark::fillRandom(size_t count, int minVal, int maxVal) {
    MemoryTracker tracker("ArrayBenchmark::fillRandom");
    
    std::random_device rd;
    std::mt19937 gen(rd());
    std::uniform_int_distribution<int> dist(minVal, maxVal);
    
    data.clear();
    data.reserve(count);
    
    for (size_t i = 0; i < count; ++i) {
        data.push_back(dist(gen));
    }
    
    updateMemoryUsage();
}

void ArrayBenchmark::sort() {
    MemoryTracker tracker("ArrayBenchmark::sort");
    std::sort(data.begin(), data.end());
}

int ArrayBenchmark::binarySearch(int value) {
    MemoryTracker tracker("ArrayBenchmark::binarySearch");
    
    auto it = std::lower_bound(data.begin(), data.end(), value);
    if (it != data.end() && *it == value) {
        return static_cast<int>(std::distance(data.begin(), it));
    }
    return -1;
}

void ArrayBenchmark::updateMemoryUsage() {
    memoryUsage = data.capacity() * sizeof(int) + sizeof(*this);
}

size_t ArrayBenchmark::getMemoryUsage() const {
    return memoryUsage;
}

void ArrayBenchmark::resetMemoryTracking() {
    memoryUsage = sizeof(*this);
    updateMemoryUsage();
}
```

### 5. Main Benchmark Class

#### Benchmark.h
```cpp
#ifndef BENCHMARK_H
#define BENCHMARK_H

#include "DataStructure.h"
#include "Statistics.h"
#include "MemoryProfiler.h"
#include <vector>
#include <memory>
#include <chrono>
#include <string>
#include <fstream>

class Benchmark {
private:
    std::vector<std::unique_ptr<DataStructure>> dataStructures;
    std::vector<BenchmarkResult> results;
    
    // Benchmark configuration
    struct BenchmarkConfig {
        std::vector<size_t> testSizes;
        size_t iterations;
        bool measureMemory;
        bool exportCSV;
        std::string outputFile;
        std::vector<std::string> operations;
        
        BenchmarkConfig() 
            : testSizes({100, 1000, 10000, 100000}),
              iterations(100),
              measureMemory(true),
              exportCSV(false),
              outputFile("benchmark_results.csv"),
              operations({"insert", "search", "remove"}) {}
    };
    
    BenchmarkConfig config;
    
    // Benchmark execution
    BenchmarkResult runSingleBenchmark(DataStructure* ds, 
                                     const std::string& operation, 
                                     size_t dataSize, 
                                     size_t iterations);
    
    void runOperationBenchmark(const std::string& operation);
    void runMemoryBenchmark();
    void runScalabilityBenchmark();
    
    // Analysis
    void analyzeResults();
    void generateComparisonReport();
    void exportToCSV();
    
public:
    Benchmark();
    
    // Configuration
    void setTestSizes(const std::vector<size_t>& sizes);
    void setIterations(size_t iterations);
    void setMeasureMemory(bool measure);
    void setExportCSV(bool export);
    void setOutputFile(const std::string& filename);
    void setOperations(const std::vector<std::string>& ops);
    
    // Data structure management
    void addDataStructure(std::unique_ptr<DataStructure> ds);
    void removeDataStructure(const std::string& name);
    void clearDataStructures();
    
    // Benchmark execution
    void runAllBenchmarks();
    void runQuickBenchmark();
    void runCustomBenchmark(const std::vector<std::string>& operations);
    
    // Results and reporting
    const std::vector<BenchmarkResult>& getResults() const;
    void printResults() const;
    void saveResults(const std::string& filename) const;
    void generateReport(const std::string& filename) const;
    
    // Utility
    void reset();
    size_t getDataStructureCount() const;
    std::vector<std::string> getDataStructureNames() const;
};

#endif // BENCHMARK_H
```

#### Benchmark.cpp (Key Methods)
```cpp
#include "Benchmark.h"
#include <iostream>
#include <iomanip>
#include <algorithm>
#include <sstream>

BenchmarkResult Benchmark::runSingleBenchmark(DataStructure* ds,
                                            const std::string& operation,
                                            size_t dataSize,
                                            size_t iterations) {
    BenchmarkResult result;
    result.operation = operation;
    result.dataStructure = ds->getName();
    result.dataSize = dataSize;
    result.iterations = iterations;
    
    // Prepare data structure
    ds->clear();
    ds->fillRandom(dataSize);
    
    std::vector<double> times;
    times.reserve(iterations);
    
    // Warm-up
    for (size_t i = 0; i < 10; ++i) {
        if (operation == "insert") {
            ds->insert(9999);
        } else if (operation == "search") {
            ds->search(500);
        } else if (operation == "remove") {
            ds->remove(500);
        }
    }
    
    // Actual benchmark
    for (size_t i = 0; i < iterations; ++i) {
        auto start = std::chrono::high_resolution_clock::now();
        
        if (operation == "insert") {
            ds->insert(9999);
        } else if (operation == "search") {
            ds->search(500);
        } else if (operation == "remove") {
            ds->remove(500);
        } else if (operation == "insertAt") {
            ds->insertAt(dataSize / 2, 9999);
        } else if (operation == "removeAt") {
            ds->removeAt(dataSize / 2);
        }
        
        auto end = std::chrono::high_resolution_clock::now();
        auto duration = std::chrono::duration_cast<std::chrono::nanoseconds>(end - start);
        times.push_back(duration.count() / 1e6); // Convert to milliseconds
    }
    
    // Calculate statistics
    Statistics stats(times);
    result.averageTimeMs = stats.mean();
    result.minTimeMs = stats.min();
    result.maxTimeMs = stats.max();
    result.standardDeviation = stats.standardDeviation();
    
    if (config.measureMemory) {
        result.memoryUsage = ds->getMemoryUsage();
    }
    
    return result;
}

void Benchmark::runOperationBenchmark(const std::string& operation) {
    std::cout << "\nRunning " << operation << " benchmark..." << std::endl;
    
    for (size_t dataSize : config.testSizes) {
        std::cout << "Testing with " << dataSize << " elements..." << std::endl;
        
        for (auto& ds : dataStructures) {
            try {
                BenchmarkResult result = runSingleBenchmark(ds.get(), operation, 
                                                          dataSize, config.iterations);
                results.push_back(result);
                
                std::cout << "  " << std::setw(20) << ds->getName() 
                          << ": " << std::fixed << std::setprecision(3) 
                          << result.averageTimeMs << "ms ± " 
                          << result.standardDeviation << "ms" << std::endl;
                          
            } catch (const std::exception& e) {
                std::cout << "  " << ds->getName() << ": Error - " << e.what() << std::endl;
            }
        }
    }
}

void Benchmark::runAllBenchmarks() {
    std::cout << "Starting comprehensive benchmark..." << std::endl;
    std::cout << "Data structures: " << dataStructures.size() << std::endl;
    std::cout << "Test sizes: ";
    for (size_t size : config.testSizes) {
        std::cout << size << " ";
    }
    std::cout << std::endl;
    std::cout << "Iterations per test: " << config.iterations << std::endl;
    
    results.clear();
    
    // Run benchmarks for each operation
    for (const std::string& operation : config.operations) {
        runOperationBenchmark(operation);
    }
    
    // Run memory benchmark if enabled
    if (config.measureMemory) {
        runMemoryBenchmark();
    }
    
    // Analyze and report results
    analyzeResults();
    
    if (config.exportCSV) {
        exportToCSV();
    }
    
    std::cout << "\nBenchmark completed!" << std::endl;
}

void Benchmark::printResults() const {
    std::cout << "\n=== BENCHMARK RESULTS ===" << std::endl;
    
    // Group results by operation
    std::unordered_map<std::string, std::vector<BenchmarkResult>> groupedResults;
    for (const auto& result : results) {
        groupedResults[result.operation].push_back(result);
    }
    
    for (const auto& group : groupedResults) {
        std::cout << "\nOperation: " << group.first << std::endl;
        std::cout << std::setw(15) << "Data Structure" 
                  << std::setw(10) << "Size"
                  << std::setw(15) << "Avg Time (ms)"
                  << std::setw(15) << "Std Dev (ms)"
                  << std::setw(15) << "Memory (bytes)" << std::endl;
        std::cout << std::string(80, '-') << std::endl;
        
        for (const auto& result : group.second) {
            std::cout << std::setw(15) << result.dataStructure
                      << std::setw(10) << result.dataSize
                      << std::setw(15) << std::fixed << std::setprecision(3) << result.averageTimeMs
                      << std::setw(15) << std::fixed << std::setprecision(3) << result.standardDeviation
                      << std::setw(15) << result.memoryUsage << std::endl;
        }
    }
}
```

### 6. Report Generator

#### ReportGenerator.h
```cpp
#ifndef REPORT_GENERATOR_H
#define REPORT_GENERATOR_H

#include "Benchmark.h"
#include <string>
#include <vector>

class ReportGenerator {
private:
    const std::vector<BenchmarkResult>& results;
    
    std::string generateHTMLReport() const;
    std::string generateTextReport() const;
    std::string generateMarkdownReport() const;
    std::string generateLatexReport() const;
    
    std::string generateComparisonTable(const std::string& operation) const;
    std::string generatePerformanceChart(const std::string& operation) const;
    std::string generateMemoryUsageChart() const;
    
public:
    explicit ReportGenerator(const std::vector<BenchmarkResult>& benchmarkResults);
    
    void generateReport(const std::string& filename, 
                       const std::string& format = "html") const;
    
    std::string generateSummary() const;
    std::vector<std::string> getRecommendations() const;
};

#endif // REPORT_GENERATOR_H
```

## User Interface

### Main Application
```cpp
#include "Benchmark.h"
#include "ArrayBenchmark.h"
#include "LinkedListBenchmark.h"
#include "StackBenchmark.h"
#include "QueueBenchmark.h"
#include <iostream>
#include <memory>

class BenchmarkApp {
private:
    Benchmark benchmark;
    
    void displayMenu() const;
    void addDataStructures();
    void configureBenchmark();
    void runQuickBenchmark();
    void runFullBenchmark();
    void runCustomBenchmark();
    void viewResults();
    void exportResults();
    void help() const;
    
public:
    void run();
};

void BenchmarkApp::addDataStructures() {
    benchmark.addDataStructure(std::make_unique<ArrayBenchmark>());
    benchmark.addDataStructure(std::make_unique<LinkedListBenchmark>());
    benchmark.addDataStructure(std::make_unique<StackBenchmark>());
    benchmark.addDataStructure(std::make_unique<QueueBenchmark>());
    
    std::cout << "Added " << benchmark.getDataStructureCount() 
              << " data structures for benchmarking." << std::endl;
}

void BenchmarkApp::runQuickBenchmark() {
    std::cout << "Running quick benchmark..." << std::endl;
    
    // Quick configuration
    benchmark.setTestSizes({1000, 10000});
    benchmark.setIterations(50);
    benchmark.setOperations({"insert", "search"});
    
    benchmark.runCustomBenchmark({"insert", "search"});
    benchmark.printResults();
}

int main() {
    std::cout << "===========================================" << std::endl;
    std::cout << "     DATA STRUCTURE BENCHMARK v1.0        " << std::endl;
    std::cout << "===========================================" << std::endl;
    
    try {
        BenchmarkApp app;
        app.run();
    } catch (const std::exception& e) {
        std::cerr << "Fatal error: " << e.what() << std::endl;
        return 1;
    }
    
    std::cout << "Thank you for using Data Structure Benchmark!" << std::endl;
    return 0;
}
```

## Cross-Platform Considerations

### CMakeLists.txt
```cmake
cmake_minimum_required(VERSION 3.10)
project(DataStructureBenchmark)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# Compiler flags
if(MSVC)
    set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} /W4 /O2")
else()
    set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wall -Wextra -Wpedantic -O2")
endif()

# Source files
set(SOURCES
    src/Benchmark.cpp
    src/ArrayBenchmark.cpp
    src/LinkedListBenchmark.cpp
    src/StackBenchmark.cpp
    src/QueueBenchmark.cpp
    src/Statistics.cpp
    src/MemoryProfiler.cpp
    src/ReportGenerator.cpp
    src/main.cpp
)

# Create executable
add_executable(${PROJECT_NAME} ${SOURCES})

# Include directories
target_include_directories(${PROJECT_NAME} PRIVATE src)

# Optional: Gnuplot for graphing
find_package(PkgConfig QUIET)
if(PkgConfig_FOUND)
    pkg_check_modules(GNUPLOT gnuplot)
    if(GNUPLOT_FOUND)
        target_compile_definitions(${PROJECT_NAME} PRIVATE HAVE_GNUPLOT)
        target_link_libraries(${PROJECT_NAME} ${GNUPLOT_LIBRARIES})
    endif()
endif()

# Testing
enable_testing()
add_subdirectory(tests)
```

## Testing Strategy

### Unit Tests
```cpp
#include <gtest/gtest.h>
#include "ArrayBenchmark.h"
#include "Statistics.h"
#include "MemoryProfiler.h"

class ArrayBenchmarkTest : public ::testing::Test {
protected:
    void SetUp() override {
        array = std::make_unique<ArrayBenchmark>();
    }
    
    std::unique_ptr<ArrayBenchmark> array;
};

TEST_F(ArrayBenchmarkTest, BasicOperations) {
    array->insert(10);
    array->insert(20);
    array->insert(30);
    
    EXPECT_EQ(array->size(), 3);
    EXPECT_EQ(array->search(20), 1);
    EXPECT_EQ(array->get(0), 10);
    
    array->remove(20);
    EXPECT_EQ(array->size(), 2);
    EXPECT_EQ(array->search(20), -1);
}

TEST_F(ArrayBenchmarkTest, MemoryTracking) {
    size_t initialMemory = array->getMemoryUsage();
    
    array->fillRandom(1000);
    size_t finalMemory = array->getMemoryUsage();
    
    EXPECT_GT(finalMemory, initialMemory);
}

class StatisticsTest : public ::testing::Test {
protected:
    void SetUp() override {
        data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        stats = std::make_unique<Statistics>(data);
    }
    
    std::vector<double> data;
    std::unique_ptr<Statistics> stats;
};

TEST_F(StatisticsTest, BasicStatistics) {
    EXPECT_DOUBLE_EQ(stats->mean(), 5.5);
    EXPECT_DOUBLE_EQ(stats->median(), 5.5);
    EXPECT_DOUBLE_EQ(stats->min(), 1.0);
    EXPECT_DOUBLE_EQ(stats->max(), 10.0);
}

TEST_F(StatisticsTest, Percentiles) {
    EXPECT_DOUBLE_EQ(stats->percentile(0.25), 3.25);
    EXPECT_DOUBLE_EQ(stats->percentile(0.75), 7.75);
}
```

## Performance Requirements
- Support benchmarking up to 1,000,000 elements
- Complete benchmark suite in < 10 minutes
- Memory usage tracking with < 1% overhead
- Statistical analysis in < 1 second
- Generate reports in < 5 seconds

## Extension Ideas
- GPU benchmark comparison
- Cache performance analysis
- Multi-threaded data structure benchmarking
- Real-time performance monitoring
- Machine learning-based performance prediction
- Web-based visualization dashboard
- Integration with continuous integration systems

## Deliverables
1. Complete source code with proper class structure
2. CMake and Makefile build configurations
3. Comprehensive unit tests
4. Documentation (README, code comments)
5. Sample benchmark configurations
6. Build and run scripts for all platforms
7. Performance analysis reports

## Success Criteria
- All benchmark operations work correctly
- Memory tracking is accurate and efficient
- Statistical analysis provides meaningful insights
- Code compiles and runs on all target platforms
- Unit tests achieve 90% code coverage
- Performance meets specified requirements
- Generated reports are comprehensive and useful
